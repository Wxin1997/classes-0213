package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala20_Collection_Method3 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法 - 操作数据
    // 对多个集合数据进行操作
    val list1: List[Int] = List(1, 2, 3, 4)
    val list2: List[Int] = List(3, 4, 5, 6, 7)

    //并集，合集
    println(list1.union(list2))
    //交集
    println(list1.intersect(list2))
    //差集
    println(list1.diff(list2))

    //两个集合数据进行关联，相同位置的关联
    //拉链
    val zipList: List[(Int, Int)] = list1.zip(list2)
    println(zipList)

    //自关联

  }


}
