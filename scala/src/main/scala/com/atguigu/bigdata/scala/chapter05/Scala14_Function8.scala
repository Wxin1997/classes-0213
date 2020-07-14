package com.atguigu.bigdata.scala.chapter05

import scala.util.control.Breaks._

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala14_Function8 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 控制抽象
    //捕捉异常
    //breakable是一个函数
    //参数列表中如果有多行逻辑，可以采用大括号代替
    //scala支持将代码逻辑作为参数传递给函数使用
    //如果函数参数想要传递代码逻辑
    breakable {
      for (i <- 1 to 5) {
        if (i == 3) {
          break
        }
        println("i = " + i)
      }
    }
    println("main ...")

   // test(println("test ..."))


  }
  def test(f: => Unit)={
    f
  }
}
