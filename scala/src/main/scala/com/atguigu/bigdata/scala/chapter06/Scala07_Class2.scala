package com.atguigu.bigdata.scala.chapter06

import java.sql.{Array => _, Date => _}

/**
 * @author wx
 * @create 2020-05-23 11:33
 */
object Scala07_Class2 {
  def main(args: Array[String]): Unit = {
    //Scala - 面向对象 - class
    //Scala中class可以继承(extends)父类
    //多态 ：一个对象在不同场合下所表示的不同的状态

    //Scala当省略类型，编译器会自动将构建对象的类型进行推断
    val user = new User07
    //如果需要使用多态操作，必须显示声明类型
    val user0 : Parent07 = new User07

  }
}

//父类
class Parent07 {

}

//子类
class User07 extends Parent07 {

}