package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-26 10:43
 */
class Scala17_Subber {

}

abstract class User17 {
  var name: String

  def test(): Unit
}

class SubberUser extends User17 {
  override var name: String = _

  override def test(): Unit = {

  }
}

