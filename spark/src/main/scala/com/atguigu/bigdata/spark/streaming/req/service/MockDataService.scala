package com.atguigu.bigdata.spark.streaming.req.service

import com.atguigu.bigdata.spark.streaming.req.dao.MockDataDao
import com.atguigu.summer.framework.core.TService

/**
 * @author wx
 * @create 2020-06-20 9:17
 */
class MockDataService extends TService {
  private val mockDataDao = new MockDataDao

  override def analysis() = {

    //mockDataDao.genMockData().foreach(println)
    val datas = mockDataDao.genMockData _
    // 生成模拟数据
    //import mockDataDao._


    // 向Kafka中发送数据
    mockDataDao.writeToKafka(datas)


  }

}
