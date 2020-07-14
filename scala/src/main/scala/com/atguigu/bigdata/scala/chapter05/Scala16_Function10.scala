package com.atguigu.bigdata.scala.chapter05

import com.atguigu.summer.framework.core.TApplication


/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala16_Function10 extends TApplication {
  def main(args: Array[String]): Unit = {
    //递归
    //1、方法执行过程中调用自身
    //2、存在可以跳出递归的逻辑， 可能会出现StackOverflowError(栈溢出)
    //3、方法调用时，传递的参数之间存在规律
    //阶乘5！
    //一个大于1的数的阶乘等于这个数乘以它减1的阶乘
    //阶乘
    // println(factorial(5))
    //  println(test(10000000))
    println(test1(5, 1))
  }

  /**
   * 阶乘
   *
   * @param num
   * @return
   */
  def factorial(num: Int): Int = {
    if (num < 1) {
      1
    } else {
      num * factorial(num - 1)
    }
  }

  def test(num: Int): Int = {
    if (num < 1) {
      1
    } else {
      num + test(num - 1)
    }
  }

  def test1(num: Int, result: Int): Int = {
    if (num < 1) {
      result
    } else {
      test1(num - 1, num + result)
    }
  }
}
