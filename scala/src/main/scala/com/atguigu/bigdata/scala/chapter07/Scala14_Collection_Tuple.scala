package com.atguigu.bigdata.scala.chapter07

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala14_Collection_Tuple {
  def main(args: Array[String]): Unit = {
    //Scala - 集合
    // 1 , zhangsan,30
    // bean :
    // json :
    // List[Any]
    //如果将无关的数据当成一个整体来使用
    //封装成bean，json，集合都不是一个好的选择
    //Scala会采用一种特殊的结构来进行封装
    //这个特殊的结构就是采用小括号,称之为元组（元素的组合）：tuple
    val date = (1, "zhangsan", 30)

    // 元组中的数据一般是没有关系的。需要通过数据的顺序访问
    //元组对象._序号
    println(date._1)
    println(date._2)
    println(date._3)

    // 元组的遍历
    //迭代器
    val iterator: Iterator[Any] = date.productIterator
    while (iterator.hasNext) {
      println(iterator.next())
    }
    //索引
    println(date.productElement(0))
  }

}
