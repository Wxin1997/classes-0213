package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-26 18:22
 */
object Scala18_Abstract {
  def main(args: Array[String]): Unit = {
    //Scala -  面向对象编程 - 抽象
    println(new SubUser18)

    //如果子类重写父类的方法，需要显示增加Override关键字来修饰
    //如果子类重写父类的抽象方法，需要直接补充完整即可
  }
}

abstract class User18 {
  def test(): Unit = {

  }
}

class SubUser18 extends User18 {
  override def test(): Unit = {

  }
}

