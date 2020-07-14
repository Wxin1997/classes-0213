package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author wx
 * @create 2020-06-13 11:43
 */
object SparkStreaming10_Window {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    ssc.sparkContext.setCheckpointDir("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    // 窗口
    val wordDS: DStream[String] = ds.flatMap(_.split(" "))
    val wordToOneDS: DStream[(String, Int)] = wordDS.map((_, 1))

    // 将多个采集周期作为计算的整体
    val windowDS: DStream[(String, Int)] = wordToOneDS.window(Seconds(9))



    val result: DStream[(String, Int)] = windowDS.reduceByKey(_ + _)

    result.print()

    //启动采集器
    ssc.start()
    //等待采集器的结束
    ssc.awaitTermination()

    //ssc.stop()

  }
}
