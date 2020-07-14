package com.atguigu.bigdata.scala.exer522

import java.io.ObjectOutputStream
import java.net.{ServerSocket, Socket}

/**
 * @author wx
 * @create 2020-05-22 22:07
 */
object PcClient {
  def main(args: Array[String]): Unit = {
    val client = new Socket("localhost", 9999)
    //发送数据
    val oos = new ObjectOutputStream(client.getOutputStream)
    val dB = (x: Int) => x * 2
    oos.writeObject(dB)
    //获取数据
    val i = client.getInputStream.read()
    println(i)
  }
}
