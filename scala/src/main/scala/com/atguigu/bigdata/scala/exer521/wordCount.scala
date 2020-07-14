package com.atguigu.bigdata.scala.exer521

import scala.io.Source

/**
 * @author wx
 * @create 2020-05-29 20:03
 */
object wordCount {
  def main(args: Array[String]): Unit = {
    //获取数据
    val dataList: List[String] = Source.fromFile("input/word.txt").getLines().toList
    val result: List[(String, Int)] = dataList.flatMap(
      data => data.split(" ")
    ).groupBy(word => word)
      .map(kv => {
        (kv._1, kv._2.size)
      })
      .toList
      .sortBy(kv => kv._2)(Ordering.Int.reverse)
      .take(3)

    println(result)

  }
}
