package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-26 18:22
 */
object Scala20_Abstract3 {
  def main(args: Array[String]): Unit = {
    //Scala -  面向对象编程 - 抽象
    // 特质
    //动态扩展功能

  }
}

trait Operate {
  //抽象的方法
  def oper(): Unit
}

//如果一个类复合特质，那么可以将特质混入到类中，采用extends关键字
//类应该将特质中的抽象方法补全
class MySQL extends Operate {
  override def oper(): Unit = {
    println("执行MySQL操作.......")
  }
}





