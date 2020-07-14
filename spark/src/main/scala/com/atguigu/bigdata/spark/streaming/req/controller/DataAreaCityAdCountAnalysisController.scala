package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.DataAreaCityAdCountAnalysisService
import com.atguigu.summer.framework.core.TController

/**
 * @author wx
 * @create 2020-06-20 20:18
 */
class DataAreaCityAdCountAnalysisController extends TController {
  private val dataAreaCityAdCountAnalysisService = new DataAreaCityAdCountAnalysisService

  override def execute(): Unit = {
    val result= dataAreaCityAdCountAnalysisService.analysis()
  }
}
