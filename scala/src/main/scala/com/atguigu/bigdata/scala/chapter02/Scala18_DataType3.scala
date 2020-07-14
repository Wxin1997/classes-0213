package com.atguigu.bigdata.scala.chapter02

/**
 * @author wx
 * @create 2020-05-19 0:15
 */
object Scala18_DataType3 {
  def main(args: Array[String]): Unit = {
    //自动转换
    //Byte类型和Short类型之间没有任何关系，但是Scala通过隐式转换的规则将Byte类型自动转换成Short类型
    val b: Byte = 10
    val s: Short = b
    val i: Int = s

    //如果将精度大的类型转换成精度小的类型
    // java : 截取
    //Scala : 方法转换
    val bb: Byte = i.toByte

  }
}
