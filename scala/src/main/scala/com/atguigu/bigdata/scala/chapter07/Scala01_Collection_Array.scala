package com.atguigu.bigdata.scala.chapter07

/**
 * @author wx
 * @create 2020-05-26 16:00
 */
object Scala01_Collection_Array {
  def main(args: Array[String]): Unit = {
    //Scala - 集合 - 数组

    //创建数组对象
    //数组的字符串打印：
    //可以设定泛型，改变数组的类型

    val array = new Array[String](5)

    //向数组中添加数据
    //scala中范围数组指定的元素需要采用小括号，而不是中括号
    array(0) = "zhangsan0"
    array(1) = "zhangsan1"
    array(2) = "zhangsan2"
    array(3) = "zhangsan3"
    array(4) = "zhangsan4"


    //遍历数组中的数据

    //for循环遍历
    //    for (s <- array) {
    //      println("S = " + s)
    //    }

    println(array.mkString(","))


  }
}
