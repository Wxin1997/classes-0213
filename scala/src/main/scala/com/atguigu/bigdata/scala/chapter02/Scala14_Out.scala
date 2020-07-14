package com.atguigu.bigdata.scala.chapter02

import java.io.{FileWriter, PrintWriter}

import scala.io.Source

/**
 * @author wx
 * @create 2020-05-19 0:15
 */
object Scala14_Out {
  def main(args: Array[String]): Unit = {
    //输出
    val out = new PrintWriter(
      new FileWriter("output/test.txt")
    )
    out.println("zhangsan")
    out.flush()
    out.close()
  }
}
