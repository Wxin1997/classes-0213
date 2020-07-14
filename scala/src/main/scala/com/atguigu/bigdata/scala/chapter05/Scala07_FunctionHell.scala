package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala07_FunctionHell {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版

    //1、函数可以作为对象赋值给变量
    def test1(): String = {
      "zhangsan"
    }

    //将函数赋值给变量，那么这个变量其实就是函数，可以调用
    //函数如果没有参数列表，那么调用时可以省略小括号
    // 如果此时希望将函数不执行，而是当成一个整体赋值给变量，那么需要使用下划线

    //val f1 = test1 _
    //println(f1())

    val f1: () => String = test1

    def test11(i: Int): Int = {
      i * 2
    }

    val f11: (Int) => Int = test11
    println(f11(10))
    //2、函数可以作为参数传递给其他的函数

    //3、函数可以作为函数的返回值返回


  }
}
