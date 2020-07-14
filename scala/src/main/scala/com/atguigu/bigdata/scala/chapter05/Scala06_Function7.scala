package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala06_Function7 {
  def main(args: Array[String]): Unit = {
    //当只关心代码逻辑，不关心函数名称时，函数名和def关键字可以省略
    //将没有名称和def关键字的函数称之为匿名函数
    // 规则 ： （参数列表） =>  {代码逻辑}
    //函数调用  ： 函数（变量）名称（参数列表）

    val a = () => println("no name")
    //调用
    a()
  }
}
