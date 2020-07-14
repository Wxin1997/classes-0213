package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable.ArrayBuffer

/**
 * @author wx
 * @create 2020-05-26 16:00
 */
object Scala03_Collection_Array2 {
  def main(args: Array[String]): Unit = {

    //Scala - 集合 - 数组  - 可变
    // 类似于StringBuilder
    //可变数组在mutable
    val array = new ArrayBuffer[String]()

    //数据的操作
    //追加数据
    array.append("a")
    array.append("b")


    //遍历数据
    println(array)
    //    for (s <- array) {
    //      println("s = " + s)
    //    }

  }
}
