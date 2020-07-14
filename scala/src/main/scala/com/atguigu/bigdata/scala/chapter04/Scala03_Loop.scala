package com.atguigu.bigdata.scala.chapter04

/**
 * @author wx
 * @create 2020-05-19 21:42
 */
object Scala03_Loop {
  def main(args: Array[String]): Unit = {
    // Scala 循环
    /*
    Java : for(int i =0 ;i<10;i++){
           }
     */
    for (i <- 1 to 5) {
      // println("i = " + i)
    }

    for (i <- 1 until 5) {
      //println("i = " + i)
    }

    for (i <- Range(1, 5)) {
      //println("i = " + i)
    }
    for (i <- Range(1, 5, 2)) {
     // println("i = " + i)
    }

    for (i <- 1 to 5 by 2) {
      println("i = " + i)
    }
  }
}
