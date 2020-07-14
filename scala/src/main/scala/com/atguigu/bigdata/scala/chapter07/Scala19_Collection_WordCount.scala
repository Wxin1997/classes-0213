package com.atguigu.bigdata.scala.chapter07

import scala.io.Source

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala19_Collection_WordCount {
  def main(args: Array[String]): Unit = {
    //Scala - WordCount
    //需求 :将文件中单词统计出现的次数并排序取前三名
    //spark => wordcount

    //代码实现
    //TODO 1.从文件中获取数据,获取一行一行的字符串
    //可迭代的集合都可以互相转换
    val datalist: List[String] = Source.fromFile("input/word.txt").getLines().toList

    //TODO 2. 将一行一行的字符串拆分为一个一个的单词
    val wordlist: List[String] = datalist.flatMap(
      data => {
        data.split(" ")
      })

    //TODO 3. 根据单词将数据进行分组,相同的单词分到同一个组中
    val wordGroupMap: Map[String, List[String]] = wordlist.groupBy(
      word => word
    )


    //TODO 4. 将分组后的数据进行次数的统计: word,count
    //A=>B
    val wordToCountMap: Map[String, Int] = wordGroupMap.map(
      kv => {
        (kv._1, kv._2.size)
      }
    )
    //TODO 5. 将统计的结果根据次数进行排序: 降序
    // map => list
    val sortList: List[(String, Int)] = wordToCountMap.toList.sortBy(
      kv => kv._2
    )(Ordering.Int.reverse)
    //TODO 6. 用take取前三
    val result: List[(String, Int)] = sortList.take(3)
    println(result)
  }


}
