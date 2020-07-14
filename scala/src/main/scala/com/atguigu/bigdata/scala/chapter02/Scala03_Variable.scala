package com.atguigu.bigdata.scala.chapter02

/**
 * @author wx
 * @create 2020-05-18 22:34
 */
object Scala03_Variable {
  def main(args: Array[String]): Unit = {
     //final 不能直接修饰变量
    //scala 为了让变量声明后不能发生修改，并且不能使用final关键字的场合
        //TODO 产生关键字val 声明变量， 声明后的变量无法修改
    val name : String = "zhangsan"
    println(name)
  }
}
