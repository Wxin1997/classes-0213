package com.atguigu.bigdata.scala.chapter05

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.ServerSocket

import com.atguigu.summer.framework.bean.Task
import com.atguigu.summer.framework.core.TApplication

/**
 * @author wx
 * @create 2020-05-24 15:18
 */
object Scala18_Function_Server extends TApplication {
  def main(args: Array[String]): Unit = {
    start("serverSocket") {
      val server = envData.asInstanceOf[ServerSocket]
      while (true) {
        val client = server.accept()
        new Thread(
          new Runnable {
            override def run(): Unit = {
              //In
              val inObject = new ObjectInputStream(client.getInputStream)
              val task = inObject.readObject().asInstanceOf[Task]
              client.shutdownInput()

              //Out
              val outObject = new ObjectOutputStream(client.getOutputStream)
              val result = task.compute()
              outObject.writeObject(result)
              outObject.close()

              if (!client.isClosed) {
                client.close()
              }

            }
          }
        ).start()
      }
    }
  }
}
