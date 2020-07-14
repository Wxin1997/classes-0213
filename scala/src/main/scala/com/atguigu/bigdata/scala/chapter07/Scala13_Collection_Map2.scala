package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala13_Collection_Map2 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - Map - 映射 - 可变
    val map: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    //获取指定 key的value
    // map 集合可以放null键null 值
    // java ： map.get(key)
    //Option: 选项类型
    //        有值： Some 根据key可以获取值
    //        无值： None 根据key获取不到值
    val maybeInt: Option[Int] = map.get("d")
    println(maybeInt)
    //从None对象中获取值会发生异常
    //println(maybeInt.get)
    println(maybeInt.getOrElse(-1))


  }

}
