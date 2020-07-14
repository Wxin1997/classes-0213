package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala08_FunctionHell1 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版
    //2、函数可以作为参数传递给其他的函数
    // 函数名 （参数名1:参数类型,参数名2:参数类型)
    // 函数名( 参数名1: 函数类型)
    def fun2(i: Int): Int = {
      i * 2
    }

    def test2(f: (Int) => Int) = {
      f(10)
    }

    //2、1 将函数赋值为变量
    val f = fun2 _
    println(test2(f))

    //2、2将函数作为参数使用时，一般不关心函数的名称，一般使用匿名函数

    //匿名函数的规则
    //val result = test2((i:Int) => {i*i})
    //println(result)

    //至简原则
    //val result = test2((i: Int) => i * i)
    //val result = test2((i) => i * 2)
    // val result = test2(i => i * 2)
    val result = test2(_ * 2)
    println(result)
    //3、函数可以作为函数的返回值返回


  }
}
