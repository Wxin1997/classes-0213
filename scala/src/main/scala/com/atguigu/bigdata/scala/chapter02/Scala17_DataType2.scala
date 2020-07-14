package com.atguigu.bigdata.scala.chapter02

/**
 * @author wx
 * @create 2020-05-19 0:15
 */
object Scala17_DataType2 {
  def main(args: Array[String]): Unit = {
    // Null
    //Scala 将null作为一个特殊的对象进行处理，类型就是Null
    val s = null
    val ss: String = null
    //Nil空集合
    val nil: List[String] = Nil
    //Object =>引用数据类型
    // Any => AnyRef
    //     => AnyVal
    val a: Any = "123"
  }

  def test(): String = {
    throw new Exception
  }
}
