package com.atguigu.bigdata.scala.chapter03

/**
 * @author wx
 * @create 2020-05-19 19:27
 */
object Scala01_Operator1 {
  def main(args: Array[String]): Unit = {
    val a = new String("abc")
    val b = new String("abc")
    println(a == b) //true
    println(a.equals(b)) //true
    println(a eq b) //false
  }
}
