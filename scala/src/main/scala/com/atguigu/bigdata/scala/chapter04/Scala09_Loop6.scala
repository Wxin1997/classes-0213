package com.atguigu.bigdata.scala.chapter04

import scala.util.control.Breaks. _

/**
 * @author wx
 * @create 2020-05-19 21:42
 */
object Scala09_Loop6 {
  def main(args: Array[String]): Unit = {
    //循环-中断
    //循环中没有break关键字
    //Scala采用对象，方法的方式实现中断操作，使用抛出异常的方式来中断循环
    breakable {
      for (i <- 1 to 5) {
        if (i == 3) {
          //中断循环
          break()
        }
        println("i = " + i)

      }

    }
    println("yyyy")
  }
}
