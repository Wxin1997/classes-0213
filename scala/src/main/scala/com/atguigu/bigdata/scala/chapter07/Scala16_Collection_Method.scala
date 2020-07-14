package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala16_Collection_Method {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法
    val list = List(1, 2, 3, 4)
    //获取集合的长度
    // println(list.length)
    // println(list.size)

    //遍历数据
    // list.foreach(println)
    //println(list.mkString(","))
    //val iterator: Iterator[Int] = list.iterator

    //判断是否为空
    //println(list.isEmpty)

    //简单运算
    println(list.sum)
    println(list.max)
    println(list.min)
    //乘积
    println(list.product)
  }

}
