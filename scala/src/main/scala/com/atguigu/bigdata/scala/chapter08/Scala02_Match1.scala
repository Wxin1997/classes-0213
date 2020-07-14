package com.atguigu.bigdata.scala.chapter08

/**
 * @author wx
 * @create 2020-05-30 9:14
 */
object Scala02_Match1 {
  def main(args: Array[String]): Unit = {
    // Scala - 模式匹配 - 规则
    def describe(x: Any) = x match {
      case 5 => "Int five"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "Char +"
    }
  }
}
