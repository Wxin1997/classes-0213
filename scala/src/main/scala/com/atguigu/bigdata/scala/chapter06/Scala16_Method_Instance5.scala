package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala16_Method_Instance5 {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象 - 构造方法
    val user = new User("wangwu")

  }

  class Person(name: String) {
    println("AAAA")

    def this() = {
      this("zhangsan")
      println("BBBB")
    }
  }

  class User() extends Person("zhangsan") {
    println("CCCC")

    def this(z: String) = {
      this()
      println("DDDD")
    }
  }


}
