package com.atguigu.summer.framework.core

import java.net.{ServerSocket, Socket}

import com.atguigu.summer.framework.util.{EnvUtil, PropertiesUtil}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}


/**
 * @author wx
 * @create 2020-05-22 21:11
 */
trait TApplication {
  var envData: Any = null

  /*
   开发原则 ： OCP Open - Close （开闭原则）
   Open :  开发的程序对功能的扩展开放
   Close:  在扩展的同时不应该对原有的代码进行修改。
   */
  /**
   * 启动应用
   * 函数柯里化
   * 控制抽象
   *
   * @t 参数类型： jdbc, file, hive, kafka, socket, serverSocket
   */
  def start(t: String)(op: => Unit)(implicit time: Duration = Seconds(5)) = {
    //1、初始化环境
    if (t == "socket") {
      envData = new Socket(PropertiesUtil.getValue("server.host"), PropertiesUtil.getValue("server.port").toInt)
    } else if (t == "serverSocket") {
      envData = new ServerSocket(PropertiesUtil.getValue("server.port").toInt)
    } else if (t == "spark") {
      //1、准备Spark环境

      envData = EnvUtil.getEnv()
    } else if (t == "sparkStreaming") {

      envData = EnvUtil.getStreamingEnv()
    }


    //2、业务逻辑


    try {
      op
    } catch {
      case ex: Exception => println("业务执行失败" + ex.getMessage)
    }


    //3、环境关闭
    if (t == "socket") {
      val socket = envData.asInstanceOf[Socket]
      if (!socket.isClosed) {
        socket.close()
      }
    } else if (t == "serverSocket") {
      val server = envData.asInstanceOf[ServerSocket]
      if (!server.isClosed) {
        server.close()
      }
    } else if (t == "spark") {
      EnvUtil.clear()
    } else if (t == "sparkStreaming") {
      val ssc = envData.asInstanceOf[StreamingContext]
      ssc.start()
      ssc.awaitTermination()
    }
  }
}
