package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala17_Collection_Method1 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法
    val list = List(1, 2, 3, 4, 5, 6, 7, 8)
    //    //获取集合的头
    //    println(list.head)
    //    //获取集合的尾
    //    println(list.tail)
    //
    //    //最后一个
    //    println(list.last)
    //    //初始
    //    println(list.init)
    //
    //    //反转
    //    println(list.reverse)
    //    println(list.reverse.head)
    //
    //    //判断数据是否存在
    //    println(list.contains(5))
    //
    //    //数据去重
    //    println(list.toSet)
    //    println(list.distinct)

    //取(拿)数据
    //println(list.take(3))
    //println(list.takeRight(3))

    //丢弃数据
    println(list.drop(2))
    println(list.dropRight(2))
  }

}
