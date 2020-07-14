package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala25_Collection_Queue {
  def main(args: Array[String]): Unit = {
    //
    val map1 = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = mutable.Map("a" -> 4, "d" -> 5, "c" -> 6)
    val foldMap: mutable.Map[String, Int] = map1.foldLeft(map2)(
      (map, kv) => {
        val k: String = kv._1
        val v: Int = kv._2
        val newValue: Int = map.getOrElse(k, 0) + v
        map.update(k, newValue)
        map
      }
    )
    println(foldMap)

  }


}
