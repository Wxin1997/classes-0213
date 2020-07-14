package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-26 16:00
 */
object Scala02_Collection_Array1 {
  def main(args: Array[String]): Unit = {

    //Scala - 集合 - 数组
    //可变集合，不可变集合
    // Array 不可变数组集合
    //对不可变集合的数据操作会产生新的数组

    //采用其他方式创建数组
    //apply 方法
    val array = Array(1, 2, 3, 4)

    //添加数据
    //使用:+ 5 表示向数组的后面添加数据
    //使用+: 5 表示向数组的前面添加数据
    val newArray = array :+ 5

    println(array.mkString(","))
    println(newArray.mkString(","))

    val newArray1 = 5 +: array
    newArray1.mkString(",")


  }
}
