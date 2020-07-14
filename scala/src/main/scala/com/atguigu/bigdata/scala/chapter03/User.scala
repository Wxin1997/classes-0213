package com.atguigu.bigdata.scala.chapter03

/**
 * @author wx
 * @create 2020-05-19 19:38
 */
class User {
  def ++(s: String): Unit = {
    print("user..." + s)
  }
}
