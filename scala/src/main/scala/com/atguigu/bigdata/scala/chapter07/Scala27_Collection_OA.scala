package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-27 9:36
 */
object Scala27_Collection_OA {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 常用方法 - 折叠
    //    val list = List(1, 2, List(3, 4))
    //    list.flatMap(
    //      data => {
    //        if (data.isInstanceOf[List]) {
    //          data.asInstanceOf
    //        } else {
    //          List(data)
    //        }
    //      }
    //    )
    //排序
    val list = List(1, 4, 3, 6, 7, 2, 5)
    //println(list.sortBy(num => num))
    val list1 = List("1", "11", "3")
    val list2 = List((30, "zhangsan"), (20, "wangwu"), (20, "lisi"))
    //自定义排序
    //两两比较大小
    println(list2.sortWith(
      (left, right) => {
        if (left._1 > right._1) {
          true
        } else if (left._1 == right._1) {
          left._2 > right._2
        } else {
          false
        }
      }
    ))
  }


}
