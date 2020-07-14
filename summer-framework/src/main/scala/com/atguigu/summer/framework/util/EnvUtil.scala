package com.atguigu.summer.framework.util

import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-09 10:13
 */
object EnvUtil {
  private val scLocal = new ThreadLocal[SparkContext]
  private val sscLocal = new ThreadLocal[StreamingContext]

  def getStreamingEnv(time: Duration = Seconds(5)) = {
    var ssc: StreamingContext = sscLocal.get()
    if (ssc == null) {
      //1、准备Spark环境
      val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")

      //2、建立和Spark的链接
      ssc = new StreamingContext(sparkConf, time)
      //保存到共享内存中
      sscLocal.set(ssc)
    }
    ssc
  }


  def getEnv() = {
    var sc: SparkContext = scLocal.get()
    if (sc == null) {
      //1、准备Spark环境
      val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")

      //2、建立和Spark的链接
      sc = new SparkContext(sparkConf)
      //保存到共享内存中
      scLocal.set(sc)
    }
    sc
  }

  def clear() = {
    getEnv().stop()
    //将共享内存中的数据清楚掉
    scLocal.remove()
  }
}
