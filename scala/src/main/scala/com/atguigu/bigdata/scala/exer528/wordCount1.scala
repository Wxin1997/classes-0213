package com.atguigu.bigdata.scala.exer528

/**
 *  1. 将上面的数据进行WordCount后排序取前三名！
 *
 * @author wx
 * @create 2020-05-28 17:13
 */
object wordCount1 {
  def main(args: Array[String]): Unit = {
    val dataList = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )
    //1、将元组拆分成字符串数组，然后根据空格拆分成单词
    val wordList: List[String] = dataList.map(
      kv => (kv._1 + " ") * kv._2
    ).flatMap(
      data => data.split(" ")
    )

    // 2、将单词分组
    val wordGroupMap: Map[String, List[String]] = wordList.groupBy(
      word => word
    )
    //3、将分组的单词进行统计个数
    val wordToCountMap: Map[String, Int] = wordGroupMap.map(
      kv => {
        (kv._1, kv._2.size)
      }
    )
    //4、将统计的结果根据次数进行排序: 降序
    val sortList: List[(String, Int)] = wordToCountMap.toList.sortBy(
      kv => kv._2
    )(Ordering.Int.reverse)
    //5. 用take取前三
    val result: List[(String, Int)] = sortList.take(3)
    println(result)
  }
}
