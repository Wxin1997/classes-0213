package com.atguigu.bigdata.scala.chapter04

/**
 * @author wx
 * @create 2020-05-19 21:42
 */
object Scala04_Loop1 {
  def main(args: Array[String]): Unit = {
    // Scala 循环
    // 循环守卫
    for (i <- Range(1, 5) if i != 3) {
      println("i = " + i)
    }
  }
}
