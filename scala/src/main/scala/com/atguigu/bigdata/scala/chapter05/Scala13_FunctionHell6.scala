package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala13_FunctionHell6 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 柯里化
    //    def test() = {
    //      def fun() = {
    //        println("xxxxxx")
    //      }
    //      fun _
    //    }
    //
    //    test()()
    //使用函数柯里化的方式声明函数
    //所谓的柯里化，其实就是多个参数列表
    //将复杂的参数逻辑简单化
    def test(i: Int)(j: Int)(f: (Int, Int) => Int) = {
      f(i, j)
    }
    // 调用函数
    println(test(1)(2)(_ + _))
  }
}
