package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala12_FunctionHell5 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版
    //1、函数使用外部的变量
    //当前场合不叫闭包
    val a = 10

    def test() = {
      val b = a + 10
      println(b)
    }

  }
}
