package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala10_Collection_Set1 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - Set -集
    // 可变集合
    val set: mutable.Set[Int] = mutable.Set(1, 2)
    // 添加数据
    set.add(3)

    //修改数据
    set.update(3, true)

    //删除数据
    set.remove(2)

    println(set)
  }

}
