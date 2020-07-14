package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala23_Collection_Method6 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 合并集合
    //Map(a->1, b->2, c->3)
    //Map(a->4, d->5, c->6)
    //=>
    //Map(a->5, b->2, d->5, c->9)
    val map1 = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = mutable.Map("a" -> 4, "d" -> 5, "c" -> 6)
    val foldMap: mutable.Map[String, Int] = map1.foldLeft(map2)(
      (map, kv) => {
        val k = kv._1
        val v = kv._2
        val newVal: Int = map.getOrElse(k, 0) + v
        map(k) = newVal
        map
      }
    )
    println(foldMap)
  }


}
