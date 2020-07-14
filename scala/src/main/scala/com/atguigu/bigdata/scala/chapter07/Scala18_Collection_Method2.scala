package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala18_Collection_Method2 {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法 - 操作数据
    //val list = List(1, 2, 3, 4, 5, 6)

    // 映射转换 A => B
    //    val newList: List[Int] = list.map(_ * 2)
    //    println(newList)
    // 扁平化  ： 将整体拆分成一个一个的个体使用的方法。
    // 扁平化操作默认只能对外层数据进行操作。内层数据无法操作.
    // 1::list:::Nil
    // val list1 = List(List(1, 2), List(3, 4))
    //1,2,3,4 => 2,4,6,8
    //    println(list1)
    //    println(list1.flatten)
    //TODO 扁平映射 = 扁平化(自定义规则) + 映射
    // val list = List(List(1, 2), List(3, 4))

    //println(list.flatten.map(_ * 2))
    //    def transform(list: List[Int]): List[Int] = {
    //      list.map(_ * 2)
    //    }
    //
    //    println(list.flatMap(_.map(_ * 2)))
    //val list = List("hello scala", "hello spark")

    // println(list.flatten)
    //    def transform(s: String) = {
    //      s.split(" ")
    //    }
    //
    //    println(list.flatMap(transform))
    //println(list.flatMap(_.split(" ")))
  }


}
