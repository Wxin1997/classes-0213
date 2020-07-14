package com.atguigu.bigdata.scala.chapter06

import sun.management.Agent

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala14_Method_Instance2 {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象 - 构造方法
      new User("zhangsan",22)

  }

  class User(name: String) {
    println("AAAA")

    def this() = {
      this("zhangsan")
      println("BBBB")
    }

    def this(name: String, age: Int) = {
      this()
      println("CCCC")
    }


  }

}
