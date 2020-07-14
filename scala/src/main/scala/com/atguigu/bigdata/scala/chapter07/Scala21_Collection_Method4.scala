package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala21_Collection_Method4 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法 - 操作数据

    // 计算
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    //sum


    //mapReduce
    //TODO 简化，规约
    //op : (A1,A1) => A1
    //reduce中传递的参数的规则：参数和返回值类型相同
    //这里的参数其实就是集合中数据的类型
    //scala中集合的计算基本数上都是两两计算
    // val i: Int = list.reduce((a: Int, b: Int) => a + b)
    val i: Int = list.reduce(_ + _)
    println(i)
    val iterator: Iterator[List[Int]] = list.sliding(3, 2)
    while (iterator.hasNext) {
      println(iterator.next().sum)
    }
  }


}
