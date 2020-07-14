package com.atguigu.bigdata.scala.chapter05

import com.atguigu.summer.framework.core.TApplication


/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala17_Function11 extends TApplication {
  def main(args: Array[String]): Unit = {
    //函数式编程 ——————  惰性编程
    def test() = {
      println("xxxxx")
      "zhangsan"
    }

    lazy val name = test()
    println("******************************")
    println("name = " + name)

  }
}
