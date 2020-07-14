package com.atguigu.bigdata.scala.exer522

/**
 * @author wx
 * @create 2020-05-29 22:14
 */
object wordCount {
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
    //清洗数据 ("wangwu", "河北", "帽子") =>("河北-帽子")
    val list: List[String] = dataList.map(
      data => {
        (data._2 + "-" + data._3)
      }
    )
    //分组
    val dataToListMap: Map[String, List[String]] = list.groupBy(data => data)
    val dataToCountMap: Map[String, Int] = dataToListMap.mapValues(_.size)
    val prvToItemAndCountList: List[(String, (String, Int))] = dataToCountMap.toList.map(
      kv => {
        val k: String = kv._1
        val v: Int = kv._2
        val ks: Array[String] = k.split("-")
        (ks(0), (ks(1), v))
      }
    )
    val groupMap: Map[String, List[(String, (String, Int))]] = prvToItemAndCountList.groupBy(_._1)
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
