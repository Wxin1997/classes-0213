package com.atguigu.bigdata.scala.exer528

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 *  1. 将上面的数据进行WordCount后排序取前三名！
 *
 * @author wx
 * @create 2020-05-28 17:13
 */
object wordCount2 {
  def main(args: Array[String]): Unit = {
    val dataList = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )
    //将dataList的kv._1 拆分成单词数组，遍历数组并于kv._2合成元组添加到新的数组中
    val list: List[List[(String, Int)]] = dataList.map(
      kv => {
        val strings: Array[String] = kv._1.split(" ")
        val dateList = new ArrayBuffer[(String, Int)]()
        for (i <- strings) {
          dateList.append((i, kv._2))
        }
        dateList.toList
      }
    )
    //
    val flatten: List[(String, Int)] = list.flatten
    //定义map数组存储
    val map: mutable.Map[String, Int] = mutable.Map()
    //遍历flatten数组将其转存到map中，如果map中存在flatten元组的元素则kv._2相加存入map
    for (kv <- flatten) {
      //如果 kv._1 再map中
      if (map.contains(kv._1)) {
        //获取map中kv._1中的value值
        val i: Int = map.getOrElse(kv._1, 0)
        //将kv._2 + value值添加到map中
        map.update(kv._1, kv._2 + i)
      } else {
        // 否则直接添加
        map.put(kv._1, kv._2)
      }
    }
    //将统计的结果根据次数进行排序: 降序
    val sortList: List[(String, Int)] = map.toList.sortBy(
      kv => kv._2
    )(Ordering.Int.reverse)
    //用take取前三
    val result: List[(String, Int)] = sortList.take(3)
    println(result)
  }
}
