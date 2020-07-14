package com.atguigu.bigdata.scala.chapter04

/**
 * @author wx
 * @create 2020-05-19 21:34
 */
object Scala02_Flow2 {
  def main(args: Array[String]): Unit = {
    // Scala 没有三元运算符
    // 使用 if else 代替
    val age = 20
    val s: String = if (age < 20) {
      "zhangsan"
    } else {
      "lisi"
    }
    println(s)
  }
}
