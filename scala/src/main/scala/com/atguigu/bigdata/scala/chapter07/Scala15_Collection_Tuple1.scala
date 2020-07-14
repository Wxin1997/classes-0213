package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala15_Collection_Tuple1 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合
    //如果元组中的元素只有两个，这样的元组称之为对偶元组
    //也称之为键值对
    val data = ("a", 1)
    val map: mutable.Map[String, Int] = mutable.Map(("a", 1), ("b", 2), ("c", 3))

    for (kv <- map) {
      println(kv._1 + "=" + kv._2)
    }
  }

}
