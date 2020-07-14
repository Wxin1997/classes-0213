package com.atguigu.bigdata.spark.streaming.req.controller

import com.atguigu.bigdata.spark.streaming.req.service.LastHourAdCountAnalysisService
import com.atguigu.summer.framework.core.TController

/**
 * @author wx
 * @create 2020-06-20 20:50
 */
class LastHourAdCountAnalysisController extends TController {
  private val lastHourAdCountAnalysisService = new LastHourAdCountAnalysisService

  override def execute(): Unit = {
    val result = lastHourAdCountAnalysisService.analysis()
  }
}
