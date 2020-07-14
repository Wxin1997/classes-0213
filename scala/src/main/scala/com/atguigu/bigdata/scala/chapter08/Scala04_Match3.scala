package com.atguigu.bigdata.scala.chapter08

/**
 * @author wx
 * @create 2020-05-30 9:14
 */
object Scala04_Match3 {
  def main(args: Array[String]): Unit = {
    // Scala - 模式匹配 - 应用
    //val data = (1, "zhangsan", 30)
    //val map = Map("a" -> ("aa", 1))
    //模式匹配-元组
    val (id, name, age) = (1, "zhangsan", 30)
    println(name)

    val map = Map("a" -> ("aa", 1), "b" -> ("bb", 2))
    map.foreach {
      case (k1, (k2, count))
      => {
        println(count)
      }
    }

  }


}
