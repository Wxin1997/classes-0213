package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author wx
 * @create 2020-06-19 16:34
 */
object SparkStreaming03_wordCount {
  def main(args: Array[String]): Unit = {
    // Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // 执行环境
    // 从socket中获取数据，一行一行获取的
    val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    val wordDS: DStream[String] = socketDS.flatMap(_.split(" "))

    val wordToOneDS: DStream[(String, Int)] = wordDS.map((_, 1))

    val wordToSumDS: DStream[(String, Int)] = wordToOneDS.reduceByKey(_ + _)

    wordToSumDS.print()

    // 关闭
    // Driver 程序执行Streaming处理过程中不能结束
    // ssc.stop()

    // 启动采集器
    ssc.start()
    //等待采集器的结束
    ssc.awaitTermination()

  }
}
