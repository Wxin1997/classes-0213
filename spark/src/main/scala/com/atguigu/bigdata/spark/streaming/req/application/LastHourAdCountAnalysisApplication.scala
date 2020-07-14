package com.atguigu.bigdata.spark.streaming.req.application

import com.atguigu.bigdata.spark.streaming.req.controller.LastHourAdCountAnalysisController
import com.atguigu.summer.framework.core.TApplication

/**
 * @author wx
 * @create 2020-06-20 20:48
 */
object LastHourAdCountAnalysisApplication extends App with TApplication {
  start("sparkStreaming") {
    val controller = new LastHourAdCountAnalysisController
    controller.execute()
  }
}
