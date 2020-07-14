package com.atguigu.bigdata.scala.chapter06

/**
 * @author wx
 * @create 2020-05-25 9:08
 */
object Scala13_Method_Instance2 {
  def main(args: Array[String]): Unit = {
    // Scala - 面向对象 - 构造方法
    // g给构造方法增加参数的目的是什么
    // 从外部将数据传递到对象的属性中
    val user = new User("zhangsan")
    println(user.username)
    val emp = new Emp("lisi")
    println(emp.name)
  }

  class User(name: String) {
    val username: String = name
  }

  class Emp(var name: String) {

  }

}
