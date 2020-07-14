package com.atguigu.bigdata.spark.streaming.req.service

import java.sql.PreparedStatement
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.bigdata.spark.streaming.req.bean.Ad_Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.DataAreaCityAdCountAnalysisDao
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.JDBCUtil
import org.apache.spark.streaming.dstream.DStream

/**
 * @author wx
 * @create 2020-06-20 20:18
 */
class DataAreaCityAdCountAnalysisService extends TService {
  private val dataAreaCityAdCountAnalysisDao = new DataAreaCityAdCountAnalysisDao

  /**
   * 数据分析
   *
   * @return
   */
  override def analysis(): Any = {
    val messageDS: DStream[String] = dataAreaCityAdCountAnalysisDao.readKafka()
    // 将数据转换为样例类来使用
    val logDS: DStream[Ad_Click_Log] = messageDS.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val dayDS: DStream[((String, String, String, String), Int)] = logDS.map(
      log => {
        val day = sdf.format(new Date(log.ts.toLong))
        ((day, log.area, log.city, log.adid), 1)
      }
    )
    val resultDS: DStream[((String, String, String, String), Int)] = dayDS.reduceByKey(_ + _)

    //
    resultDS.foreachRDD(
      rdd => {
        rdd.foreachPartition(
          datas => {
            // 获取数据库的连接
            val conn = JDBCUtil.getConnection()
            val pstat: PreparedStatement = conn.prepareStatement(
              """
                |insert into area_city_ad_count (dt, area, city,adid, count)
                |values(?,?,?,?,?  )
                |on duplicate key
                |update count = count + ?
                |""".stripMargin)
            // 操作数据库
            datas.foreach {
              case ((dt, area, city, adid), count) => {
                pstat.setString(1, dt)
                pstat.setString(1, area)
                pstat.setString(1, city)
                pstat.setString(1, adid)
                pstat.setLong(1, count)
                pstat.setLong(1, count)
                pstat.executeUpdate()
              }


            }



            // 关闭资源
            pstat.close()
            conn.close()

          }

        )
      }
    )


  }


}
