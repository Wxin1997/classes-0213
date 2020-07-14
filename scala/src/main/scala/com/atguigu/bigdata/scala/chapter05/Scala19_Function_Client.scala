package com.atguigu.bigdata.scala.chapter05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.bigdata.scala.chapter05.Scala18_Function_Server.start
import com.atguigu.summer.framework.bean.Task
import com.atguigu.summer.framework.core.TApplication

/**
 * @author wx
 * @create 2020-05-24 15:18
 */
object Scala19_Function_Client extends TApplication {
  def main(args: Array[String]): Unit = {
    start("socket") {
      val client = envData.asInstanceOf[Socket]
      //Out -> function -> 对象
      val outObject = new ObjectOutputStream(client.getOutputStream)
      //输出
      val task = new Task
      task.data = 10
      task.logic = _ * 2


      outObject.writeObject(task)
      outObject.flush()
      client.shutdownOutput()

      //In
      val inObject = new ObjectInputStream(client.getInputStream)
      val result = inObject.readObject().asInstanceOf[Int]
      println("获取计算的结果 ：" + result)
      inObject.close()
    }
  }
}
