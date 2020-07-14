package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala09_Method {
  def main(args: Array[String]): Unit = {
    //如果将伴生对象不适用小括号操作，那么等同于将伴生对象赋值给变量，而不是调用apply方法
    //appaly方法如果想要编译器自动识别，那么不能自动识别
    val user1 = User16()
    println(user1)
  }

}

class User16 {

}

object User16 {
  def apply() = new java.util.Date()
}
