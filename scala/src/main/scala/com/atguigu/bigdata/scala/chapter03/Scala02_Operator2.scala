package com.atguigu.bigdata.scala.chapter03

/**
 * @author wx
 * @create 2020-05-19 19:27
 */
object Scala02_Operator2 {
  def main(args: Array[String]): Unit = {
    val user1 = new User
    val user2 = new User
    println(user1 == user2)
    println(user1.equals(user2))
  }
}
