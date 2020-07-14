package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala11_Method_Instance {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象 - 构造方法
    //使用new关键字创建的对象其实等同于调用类的构造方法。
    //Scala是一个完全面向对象的语言。
    //Scala也是一个完全面向函数的语言。
    //Scala中类其实也是一个函数，类名其实就是函数名，类名后面可以增加括号，表示函数参数列表
    //这个类名所代表的函数其实就是构造方法，构造函数
    val user = new User()

  }

  class User() {
    // 类体 & 函数体
    println("user .... ")
  }

}
