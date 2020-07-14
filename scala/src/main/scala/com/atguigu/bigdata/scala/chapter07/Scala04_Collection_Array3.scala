package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable.ArrayBuffer

/**
 * @author wx
 * @create 2020-05-26 16:00
 */
object Scala04_Collection_Array3 {
  def main(args: Array[String]): Unit = {

    //Scala - 集合 - 数组  - 可变
    val array = ArrayBuffer(1, 2, 3, 4)

    //数据的操作
    //追加数据，集合内部的元素发生改变，所以可变数据
    array.append(5)
    array.append(6)

    //插入数据
    //向指定的位置插入数据
    // array.insert(1, 7, 8, 9)

    //修改数据
    array(1) = 5

    //遍历数据
    println(array)
  }
}
