package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala11_FunctionHell4 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版
    //java -> method -> 压栈 -> 栈帧 -> 弹栈
    //test
    //sum


    def test(i: Int) = {
      def sum(j: Int) = {
        i + j
      }

      sum _
    }

    println(test(10)(20))
  }
}
