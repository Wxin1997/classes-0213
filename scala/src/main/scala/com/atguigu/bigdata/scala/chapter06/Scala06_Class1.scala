package com.atguigu.bigdata.scala.chapter06

import java.sql.{Array => _, Date => _}

/**
 * @author wx
 * @create 2020-05-23 11:33
 */
object Scala06_Class1 {
  def main(args: Array[String]): Unit = {
    //Scala - 面向对象 - class

    //Thread sleep ,wait 方法的区别
    //object一般用于声明静态方法，通过类名直接访问
    //如果需要通过对象访问属性或方法，那么就使用class声明
    //如果需要通过类名就可以直接访问属性或方法，那么就使用object声明

    //构建对象
    //使用class , 那么需要使用new 的方式
    val user = new User06
    println(user)
    //使用object来声明。那么可以直接使用类名
    val user06 = User066
    println(user06)

    //调用方法
    // 使用class声明的类无法通过类名直接访问方法或属性，必须构建对象
    user.test()
    //使用object声明的类可以通过类名直接访问属性和方法
    User066.test()


  }

  class User06 {
    def test() = {
      println("user....")
    }
  }

  object User066 {
    def test() = {
      println("object...")
    }
  }

}
