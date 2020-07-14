package com.atguigu.bigdata.scala.chapter04

/**
 * @author wx
 * @create 2020-05-19 21:42
 */
object Scala05_Loop2 {
  def main(args: Array[String]): Unit = {
    // Scala 循环
    //    for (i <- Range(1, 5)) {
    //      for (j <- Range(1, 4)) {
    //        println("i = " + i + ",J = " + j)
    //      }
    //    }

    for (i <- Range(1, 5); j <- Range(1, 4)) {
      //println("i = " + i + ",j = " + j)
    }
    for (i <- Range(1, 5); j = i - 1) {
      // println("i = " + i + ",j = " + j)
    }
    for (i <- 1 to 18 by 2; j = (18 - i) / 2) {
      println(" " * j + "*" * i)
    }
  }
}
