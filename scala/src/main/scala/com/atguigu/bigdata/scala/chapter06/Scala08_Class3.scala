package com.atguigu.bigdata.scala.chapter06

import java.sql.{Array => _, Date => _}

/**
 * @author wx
 * @create 2020-05-23 11:33
 */
object Scala08_Class3 {
  def main(args: Array[String]): Unit = {
    //Scala - 面向对象 - 属性
    //在类中声明属性就等同于在类中声明局部变量，可以用 var val 声明
    //可以通过对象在外部访问
    val user0 = new User08
    user0.name = "zhangsan"
    println("name = " + user0.name)

    //变量应该显示的初始化
    // 如果想要像java中类的属性初始化一样，需要采用特殊的符号：下划线
    //如果属性使用val声明。那么初始值不能使用下划线。需要显示的赋值
  }
}


class User08 {
  //属性
  var name: String = _
  var age: Int = _
}