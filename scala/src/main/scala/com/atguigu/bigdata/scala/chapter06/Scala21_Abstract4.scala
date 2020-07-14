package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-26 18:22
 */
object Scala21_Abstract4 {
  def main(args: Array[String]): Unit = {
    //Scala -  面向对象编程 - 抽象
    // 特质
    //动态扩展功能
    val mysql = new MySQL21 with Operate21
    mysql.select()
    mysql.insert()
  }
}

trait Operate21 {
  //抽象的方法
  def insert(): Unit = {
    println("insert data ...")
  }
}

//如果一个类复合特质，那么可以将特质混入到类中，采用extends关键字
//类应该将特质中的抽象方法补全
class MySQL21 {
  def select(): Unit = {
    println("select data ..")
  }
}





