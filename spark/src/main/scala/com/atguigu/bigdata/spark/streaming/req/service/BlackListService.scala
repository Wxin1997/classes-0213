package com.atguigu.bigdata.spark.streaming.req.service

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.bigdata.spark.streaming.req.bean.Ad_Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.BlackListDao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.JDBCUtil
import org.apache.parquet.schema.Types.ListBuilder
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable.ListBuffer

/**
 * @author wx
 * @create 2020-06-20 10:08
 */
class BlackListService extends TService {
  private val blackListDao = new BlackListDao

  /**
   * 数据分析
   *
   * @return
   */
  override def analysis() =  {
    // 读取 Kafka的数据
    val ds: DStream[String] = blackListDao.readKafka()

    // 将数据转换为样例类来使用
    val logDS: DStream[Ad_Click_Log] = ds.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )

    // 周期性获取黑名单的信息，判断当前用户的点击数据是否在黑名单中
    //Transform
    val reduceDS: DStream[((String, String, String), Int)] = logDS.transform(
      rdd => {
        // 周期性执行
        // 读取mysql数据库，获取黑名单信息
        val connection: Connection = JDBCUtil.getConnection()
        val pstat: PreparedStatement = connection.prepareStatement(
          """
            |select userid from black_list
            |""".stripMargin)

        val rs: ResultSet = pstat.executeQuery()
        // 黑名单的ID集合
        val blackIds = ListBuffer[String]()
        while (rs.next()) {
          blackIds.append(rs.getString(1))
        }

        rs.close()
        pstat.close()
        connection.close()

        // 如果用户在黑名单中，那么将数据过滤掉， 不会进行统计
        val filterRDD: RDD[Ad_Click_Log] = rdd.filter(
          log => {
            !blackIds.contains(log.userid)
          }
        )

        // 将正常的数据进行点击量的统计
        // 天-广告-用户
        // (key, 1) => (word, sum)
        val sdf = new SimpleDateFormat("yyyy-MM-dd")


        val mapRDD: RDD[((String, String, String), Int)] = filterRDD.map(
          log => {
            val date = new Date(log.ts.toLong)

            ((sdf.format(date), log.userid, log.adid), 1)
          }
        )

        mapRDD.reduceByKey(_ + _)
      }
    )




    // 将统计结果中超过100的用户信息拉入黑名单中
    reduceDS.foreachRDD(


      rdd => {

        val conn = JDBCUtil.getConnection()
        val pstat: PreparedStatement = conn.prepareStatement(
          """
            |insert into user_ad_count (dt, userid, adid, count)
            |values(?,?,?,?)
            |on duplicate key
            |update count = count + ?
            |""".stripMargin)
        
        val pstat1: PreparedStatement = conn.prepareStatement(
          """
            |insert into black_list (userid)
            |select userid from user_ad_count
            |where dt = ? and userid =? and adid =? and count >=100
            |on duplicate key
            |update userid = ?
            |""".stripMargin)


        rdd.foreach {
          case ((day, userid, adid), sum) => {

            // 有状态保存
            // updateStateByKey=>checkpoint=>HDFS=>产生大量的小文件
            // 统计结果应该放在mysql(redis,过期数据，自动删除)中
            //更新(新增)用户的点击数量


            pstat.setString(1, day)
            pstat.setString(2, userid)
            pstat.setString(3, adid)
            pstat.setLong(4, sum)
            pstat.setLong(5, sum)
            pstat.executeUpdate()


            // 获取最新的用户统计的数据
            // 判断是否超过100
            // 如果超过100拉入黑名单



            pstat1.setString(1, day)
            pstat1.setString(2, userid)
            pstat1.setString(3, adid)
            pstat1.setString(4, userid)

            pstat1.executeUpdate()

            pstat.close()
            pstat1.close()
            conn.close()

          }
        }
      }
    )


    ds.print()

  }
   def analysis1() =  {
    // 读取 Kafka的数据
    val ds: DStream[String] = blackListDao.readKafka()

    // 将数据转换为样例类来使用
    val logDS: DStream[Ad_Click_Log] = ds.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )

    // 周期性获取黑名单的信息，判断当前用户的点击数据是否在黑名单中
    //Transform
    val reduceDS: DStream[((String, String, String), Int)] = logDS.transform(
      rdd => {
        // 周期性执行
        // 读取mysql数据库，获取黑名单信息
        val connection: Connection = JDBCUtil.getConnection()
        val pstat: PreparedStatement = connection.prepareStatement(
          """
            |select userid from black_list
            |""".stripMargin)

        val rs: ResultSet = pstat.executeQuery()
        // 黑名单的ID集合
        val blackIds = ListBuffer[String]()
        while (rs.next()) {
          blackIds.append(rs.getString(1))
        }

        rs.close()
        pstat.close()
        connection.close()

        // 如果用户在黑名单中，那么将数据过滤掉， 不会进行统计
        val filterRDD: RDD[Ad_Click_Log] = rdd.filter(
          log => {
            !blackIds.contains(log.userid)
          }
        )

        // 将正常的数据进行点击量的统计
        // 天-广告-用户
        // (key, 1) => (word, sum)
        val sdf = new SimpleDateFormat("yyyy-MM-dd")


        val mapRDD: RDD[((String, String, String), Int)] = filterRDD.map(
          log => {
            val date = new Date(log.ts.toLong)

            ((sdf.format(date), log.userid, log.adid), 1)
          }
        )

        mapRDD.reduceByKey(_ + _)
      }
    )




    // 将统计结果中超过100的用户信息拉入黑名单中
    reduceDS.foreachRDD(
      rdd => {
        rdd.foreach {
          case ((day, userid, adid), sum) => {

            // 有状态保存
            // updateStateByKey=>checkpoint=>HDFS=>产生大量的小文件
            // 统计结果应该放在mysql(redis,过期数据，自动删除)中
            //更新(新增)用户的点击数量
            val conn = JDBCUtil.getConnection()
            val pstat: PreparedStatement = conn.prepareStatement(
              """
                |insert into user_ad_count (dt, userid, adid, count)
                |values(?,?,?,?)
                |on duplicate key
                |update count = count + ?
                |""".stripMargin)

            pstat.setString(1, day)
            pstat.setString(2, userid)
            pstat.setString(3, adid)
            pstat.setLong(4, sum)
            pstat.setLong(5, sum)
            pstat.executeUpdate()


            // 获取最新的用户统计的数据
            // 判断是否超过100
            // 如果超过100拉入黑名单

            val pstat1: PreparedStatement = conn.prepareStatement(
              """
                |insert into black_list (userid)
                |select userid from user_ad_count
                |where dt = ? and userid =? and adid =? and count >=100
                |on duplicate key
                |update userid = ?
                |""".stripMargin)

            pstat1.setString(1, day)
            pstat1.setString(2, userid)
            pstat1.setString(3, adid)
            pstat1.setString(4, userid)

            pstat1.executeUpdate()

            pstat.close()
            pstat1.close()
            conn.close()

          }
        }
      }
    )


    ds.print()

  }

}
