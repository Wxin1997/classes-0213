package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala12_Method_Instance1 {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象 - 构造方法
    // 构造方法只有一个吗？
    // no scala 会提供2钟不同类型的构造方法
    // 1. 主构造方法。 在类名后的构造方法。可以完成类的初始化。
    // 2、辅助构造方法，为了完成类初始化的辅助功能而提供的构造方法。
    //    声明方式； def this()
    //    辅助函数也有重载的概念
    val user1 = new User()
    val user2 = new User("zhangsan")
    val user3 = new User("lisi", 30)
    println(user1)
    println(user2)
    println(user3)

  }

  class User() {
    println("xxxx")

    def this(name: String) = {
      this()
    }

    def this(name: String, age: Int) = {
      this(name)
    }

  }

}
