package com.atguigu.bigdata.spark.streaming

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author wx
 * @create 2020-06-19 16:34
 */
object SparkStreaming06_DIY {
  def main(args: Array[String]): Unit = {
    // Spark环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val ds: ReceiverInputDStream[String] = ssc.receiverStream(new MyReceiver("localhost", 9999))

    ds.print()

    // 启动采集器
    ssc.start()

    //等待采集器的结束
    ssc.awaitTermination()

  }

  // 自定义数据采集器
  // 1、 继承 Receiver
  //  Receiver的构造方法有参数的，所以子类在继承时，应该传递这个参数
  // 2、重写方法
  //onStart
  //onStop
  class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {

    private var socket: Socket = _

    //
    override def onStart(): Unit = {
      socket = new Socket(host, port)
      new Thread("Socket Receiver") {
        setDaemon(true)

        def receive(): Unit = {
          val reader = new BufferedReader(
            new InputStreamReader(
              socket.getInputStream,
              "UTF-8"
            )
          )

          var s: String = null
          while (true) {
            s = reader.readLine()
            if (s != null) {
              // 将获取的数据保存到框架内部进行封装
              store(s)
            }
          }

        }

        override def run(): Unit = {
          receive()
        }
      }.start()
    }

    override def onStop(): Unit = {
      socket.close()
      socket = null
    }
  }


}
