package com.atguigu.bigdata.scala.chapter07

import scala.collection.{immutable, mutable}

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala26_Collection_Test {
  def main(args: Array[String]): Unit = {
    //TODO 不同省份商品点击排行
    //(item count) => (word, count)
    val dataList = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    //TODO 数据多余，将数据进行清洗
    //("wangwu", "河北", "帽子") => ("河北", "帽子")
    val list: List[String] = dataList.map(
      t => {
        (t._2 + "-" + t._3)
      }
    )

    //TODO 应该在统计数据时，根据省份和商品同时分组
    //group("河北-帽子") => ("河北-帽子", count)
    val dataToListMap: Map[String, List[String]] = list.groupBy(data => data)
    val dataToCountMap: Map[String, Int] = dataToListMap.mapValues(_.size)
    //TODO 将分组聚合后的数据进行结构的转换
    //("河北-帽子", count) => ("河北"(帽子, count))
    val prvToItemAndCountList: List[(String, (String, Int))] = dataToCountMap.toList.map(
      kv => {
        val k: String = kv._1
        val count: Int = kv._2
        //将key进行拆分
        val ks: Array[String] = k.split("-")
        (ks(0), (ks(1), count))
      }
    )
    //TODO 将分组聚合后的数据根据省份进行分组
    //(河北，List[(帽子, count),(衣服, count),(鞋, count)])
    val groupMap: Map[String, List[(String, (String, Int))]] = prvToItemAndCountList.groupBy(_._1)

    //TODO 将分组后的数据进行排序：降序
    val result: Map[String, List[(String, Int)]] = groupMap.mapValues(
      list => {
        val itemToCountList: List[(String, Int)] = list.map(_._2)
        itemToCountList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        )
      }
    )
    println(result)
  }


}
