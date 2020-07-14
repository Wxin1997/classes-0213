package com.atguigu.bigdata.scala.exer521

/**
 * @author wx
 * @create 2020-05-29 22:33
 */
object wordCount1 {
  def main(args: Array[String]): Unit = {
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
    //数据清洗
    println(dataList.map(data => (data._2 + "-" + data._3)) //("河北-帽子")
      .groupBy(data => data) //数据：("河北-帽子",List(("河北-帽子")("河北-帽子")("河北-帽子")))
      .mapValues(_.size) //数据：(("河北-帽子",count)
      .toList.map(kv => {
      val ks: Array[String] = kv._1.split("-")
      (ks(0), (ks(1), kv._2))
    }) //数据：("河北",("帽子",count))
      .groupBy(_._1) //数据("河北",("帽子",count))
      .mapValues(list => {
        val itemToCountList: List[(String, Int)] = list.map(_._2)
        itemToCountList.sortWith(_._2 > _._2)
      }))

  }
}
