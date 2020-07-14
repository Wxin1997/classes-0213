package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-26 18:22
 */
object Scala19_Abstract2 {
  def main(args: Array[String]): Unit = {
    //Scala -  面向对象编程 - 抽象
    //抽象属性
    println(new SubUser19)
  }
}

abstract class User19 {
  var name: String
}

class SubUser19 extends User19 {
    var name :String = "zhangsan"
}



