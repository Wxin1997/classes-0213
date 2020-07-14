package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author wx
 * @create 2020-06-13 11:43
 */
object SparkStreaming11_Window1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    ssc.sparkContext.setCheckpointDir("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    // 窗口
    val wordToOneDS = ds.map(num => ("key", num.toInt))

    val result: DStream[(String, Int)] = wordToOneDS.reduceByKeyAndWindow(
      (x, y) => {
        println(s"x = ${x}, y = ${y}")
        x + y
      },
      (a, b) => {
        println(s"a = ${a}, b = ${b}")
        a - b
      }, Seconds(9)
    )

    result.foreachRDD(rdd => rdd.foreach(println))

    //启动采集器
    ssc.start()
    //等待采集器的结束
    ssc.awaitTermination()

    //ssc.stop()

  }
}
