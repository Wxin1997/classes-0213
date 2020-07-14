package com.atguigu.bigdata.scala.chapter08

/**
 * @author wx
 * @create 2020-05-30 9:14
 */
object Scala03_Match2 {
  def main(args: Array[String]): Unit = {
    // Scala - 模式匹配 - 规则
    //匹配对象
    val user = new User("zhangsan", 20)

    val result = user match {
      case User("zhangsan", 11) => "yes"
      case _ => "no"
    }

  }

  class User(val name: String, val age: Int)

  object User {
    def apply(name: String, age: Int): User = new User(name, age)

    def unapply(user: User): Option[(String, Int)] = {
      if (user == null)
        None
      else
        Some(user.name, user.age)
    }
  }

}
