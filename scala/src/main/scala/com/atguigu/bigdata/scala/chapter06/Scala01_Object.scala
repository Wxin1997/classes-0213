package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-23 10:52
 */
object Scala01_Object {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象
    val dept = new Dept01()

    val user = new User01()
    user.dept = dept
    user.name = "lisi"
    user.test()

  }
}

class Dept01 {
  var name: String = "开发部门"
}

class User01 {
  var dept: Dept01 = null
  var name: String = "zhangsan"

  def test() = {
    println("user test ...")
  }
}