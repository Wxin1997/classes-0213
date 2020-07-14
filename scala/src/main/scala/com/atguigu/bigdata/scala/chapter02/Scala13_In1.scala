package com.atguigu.bigdata.scala.chapter02

import scala.io.Source

/**
 * @author wx
 * @create 2020-05-19 0:15
 */
object Scala13_In1 {
  def main(args: Array[String]): Unit = {
    //输入 - 文件
    val strings: Iterator[String] = Source.fromFile("input/word.txt").getLines()
    while (strings.hasNext) {
      println(strings.next())
    }
  }
}
