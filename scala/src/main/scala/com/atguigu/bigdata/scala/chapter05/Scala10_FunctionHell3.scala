package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala10_FunctionHell3 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版
    //将函数作为返回值使用
    //def test(i: Int) = {
    //  i * 2
    //}

    // def fun() = {
    //  test _
    // }

    //调用函数
    //val a = fun()
    // println(a(10))

    //println(fun()(10))

    //当函数作为返回值使用，一般使用嵌套函数
    def fun() = {
      def test(i: Int) = {
        i * 2
      }

      test _
    }

    def fun1(): Int => Int = {
      def test(i: Int): Int = {
        i * 2
      }

      test
    }


    println(fun()(10))

  }
}
