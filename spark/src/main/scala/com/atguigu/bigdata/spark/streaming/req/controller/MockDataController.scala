package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.MockDataService
import com.atguigu.summer.framework.core.TController

/**
 * @author wx
 * @create 2020-06-20 9:17
 */
class MockDataController extends TController {
  private val mockDataService = new MockDataService

  override def execute(): Unit = {
    val result = mockDataService.analysis()
  }
}
