package com.atguigu.bigdata.scala.exer522

import java.io.ObjectInputStream
import java.net.ServerSocket

/**
 * @author wx
 * @create 2020-05-22 22:07
 */
object PcServer {
  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(9999)
    val client = server.accept()
    //接收数据
    val ois = new ObjectInputStream(client.getInputStream)
    val value = ois.readObject()

    val intToInt = value.asInstanceOf[Int => Int]
    //计算数据
    val i = intToInt(2)
    //发送数据
    client.getOutputStream.write(i)


  }
}
