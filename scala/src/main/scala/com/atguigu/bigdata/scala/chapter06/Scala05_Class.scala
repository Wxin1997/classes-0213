package com.atguigu.bigdata.scala.chapter06

import java.sql.{Array => _, Date => _}

/**
 * @author wx
 * @create 2020-05-23 11:33
 */
object Scala05_Class {
  def main(args: Array[String]): Unit = {
    //Scala - 面向对象 - class

    //class 和 object 的  区别
    // object其实在编译时会产生两个类文件，一个是当前类的文件，还有一个是单例的类文件
    // es: Scala05_Class.class ,Scala05_Class$.class
    // class 只会产生当前类的class文件
    // es: Scala05_Class.class
    //class其实就是用来修饰普通类
    //而object用于修饰伴随这个类所产生的一个单例对象。用于模仿java中的静态语法。
    // object中的方法和属性都可以通过类名直接访问，类似于静态方法


  }
}
