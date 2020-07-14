package com.atguigu.bigdata.scala.chapter02

import java.io.{FileWriter, PrintWriter}

/**
 * @author wx
 * @create 2020-05-19 0:15
 */
object Scala15_DataType {
  def main(args: Array[String]): Unit = {
   //数据类型-AnyVal
    val b : Byte = 10
    val s : Short = 10
    val i : Int = 10
    val l : Long = 10L
    val f : Float = 1.0f
    val d : Double = 1.0

    val c : Char ='c'
    val flg:Boolean = true

    //Unit是一个类型，对象只有一个()
    val u : Unit = ()

  }
}
