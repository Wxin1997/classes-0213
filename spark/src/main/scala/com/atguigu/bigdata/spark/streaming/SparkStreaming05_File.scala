package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-06-19 16:34
 */
object SparkStreaming05_File {
  def main(args: Array[String]): Unit = {
    // Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val dirDS: DStream[String] = ssc.textFileStream("in")

    dirDS.print()

    // 启动采集器
    ssc.start()



    //等待采集器的结束
    ssc.awaitTermination()

  }
}
