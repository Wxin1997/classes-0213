package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala24_Collection_Method7 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 扫描
    val list = List(1, 2, 3, 4)

    //fold方法直接获取最终结果
    //scan类似于fold方法但是会将中间的处理结果也保留下来
    println(list.scan(0)(_ + _))
  }


}
