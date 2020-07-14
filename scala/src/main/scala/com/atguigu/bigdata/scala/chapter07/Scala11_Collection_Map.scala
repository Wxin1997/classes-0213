package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala11_Collection_Map {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - Map
    //A => B
    // K => V
    //map用于存储KV对对象
    //构建键值对对象
    // A -> B

    //构建对象
    // map : 无序， key 不能重复
    val map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)

    println(map)

  }

}
