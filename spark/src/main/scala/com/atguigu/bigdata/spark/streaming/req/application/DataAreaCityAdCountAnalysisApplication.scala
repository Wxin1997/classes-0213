package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.DataAreaCityAdCountAnalysisController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author wx
 * @create 2020-06-20 20:17
 */
object DataAreaCityAdCountAnalysisApplication extends App with TApplication {
  start("sparkStreaming") {
    val dataAreaCityAdCountAnalysisController = new DataAreaCityAdCountAnalysisController
    dataAreaCityAdCountAnalysisController.execute()
  }
}
