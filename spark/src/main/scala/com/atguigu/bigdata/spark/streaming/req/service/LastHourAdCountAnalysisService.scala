package com.atguigu.bigdata.spark.streaming.req.service

import com.atguigu.bigdata.spark.streaming.req.bean.Ad_Click_Log
import com.atguigu.bigdata.spark.streaming.req.dao.LastHourAdCountAnalysisDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.streaming.{Minutes, Seconds}
import org.apache.spark.streaming.dstream.DStream

/**
 * @author wx
 * @create 2020-06-20 20:50
 */
class LastHourAdCountAnalysisService extends TService {
  private val lastHourAdCountAnalysisDao = new LastHourAdCountAnalysisDao

  override def analysis() = {
    // 读取Kafka数据
    val messageDS: DStream[String] = lastHourAdCountAnalysisDao.readKafka()

    //将数据转换为样例类使用
    // 将数据转换为样例类来使用
    val logDS: DStream[Ad_Click_Log] = messageDS.map(
      data => {
        val datas: Array[String] = data.split(" ")
        Ad_Click_Log(datas(0), datas(1), datas(2), datas(3), datas(4))
      }
    )

    // 将数据进行结构的转换
    val tsToCountDS: DStream[((String, Long), Int)] = logDS.map(
      log => {
        val ts: Long = log.ts.toLong
        ((log.adid, ts / 10000 * 10000), 1)
      }
    )


    // 将数据进行分组聚合（窗口）
    val tsToSumDS: DStream[((String, Long), Int)] = tsToCountDS.reduceByKeyAndWindow(_ + _, Minutes(1), Seconds(10))


    //将数据结构根据广告分组
    val adToTimeSumDS: DStream[(String, (Long, Int))] = tsToSumDS.map {
      case ((adid, time), sum) => (adid, (time, sum))
    }
    val groupDS: DStream[(String, Iterable[(Long, Int)])] = adToTimeSumDS.groupByKey()


    //排序，显示数据
    val resultDS: DStream[(String, List[(Long, Int)])] = groupDS.mapValues(
      iter => {
        iter.toList.sortWith(
          (left, right) => {
            left._1 < right._1
          }
        )
      }
    )

    resultDS.print()

  }

}
