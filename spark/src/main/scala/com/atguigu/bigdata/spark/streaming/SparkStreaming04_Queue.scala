package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-06-19 16:34
 */
object SparkStreaming04_Queue {
  def main(args: Array[String]): Unit = {
    // Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    // 执行环境
    // 从socket中获取数据，一行一行获取的
    val queue = new mutable.Queue[RDD[String]]()
    val queueDS: InputDStream[String] = ssc.queueStream(queue)


    queueDS.print()


    // 启动采集器
    ssc.start()
    for (i <- 1 to 6) {
      val rdd: RDD[String] = ssc.sparkContext.makeRDD(List(i.toString))
      queue.enqueue(rdd)
      Thread.sleep(1000)
    }




    //等待采集器的结束
    ssc.awaitTermination()

  }
}
