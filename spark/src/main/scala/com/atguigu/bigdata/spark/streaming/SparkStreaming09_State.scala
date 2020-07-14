package com.atguigu.bigdata.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author wx
 * @create 2020-06-13 11:43
 */
object SparkStreaming09_State {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    ssc.sparkContext.setCheckpointDir("cp")

    val ds: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 9999)

    // 转换

    //数据的有状态的保存
    //将spark每个采集周期的处理结果保存起来，然后和后续的数据进行聚合

    // reduceByKey是无状态的而我们需要有状态的数据操作
    // 有状态的目的其实就是将每一个采集周期数据的计算结果临时保存起来
    // 然后再一次数据的处理时可以继续使用
    ds.flatMap(_.split(" "))
      .map((_, 1L))
      //.reduceByKey(_ + _)
      // Option : Some ,None
      // updateStateByKey 是有状态计算方法
      // 第一个参数表述 相同key的value的集合
      // 第二个参数表示 想用 key的缓冲区的数据，有可能为空
      // 这里的计算的中间结果需要保存到检查点的位置中，所以需要设定检查点路径

      .updateStateByKey[Long](

        (seq: Seq[Long], buffer: Option[Long]) => {
          val newBufferValue = buffer.getOrElse(0L) + seq.sum
          Option(newBufferValue)
        }
      )
      .print()


    //启动采集器
    ssc.start()
    //等待采集器的结束
    ssc.awaitTermination()

    //ssc.stop()

  }
}
