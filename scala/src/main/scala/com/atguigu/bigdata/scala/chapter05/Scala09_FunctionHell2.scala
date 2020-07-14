package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala09_FunctionHell2 {
  def main(args: Array[String]): Unit = {
    // 函数式编程   —————— 地狱版
    def sum(x: Int, y: Int): Int = {
      x + y
    }

    def calcAnalysis(f: (Int, Int) => Int) = {
      val boyCnt = 20;
      val girlCnt = 20;
      f(boyCnt, girlCnt)
    }

   // val f = sum _
    // println(calcAnalysis(f))
    println(calcAnalysis(_ + _))
  }
}
