package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala05_Function4_hightMare {
  def main(args: Array[String]): Unit = {
    // 函数式编程 - 噩梦版
    def test(name: String): String = {
      return "name = " + name
    }

    //1、
    def test1(name: String): String = {
      "name = " + name
    }
    // test1("zhangsan")

    //TODO 2、
    def test2(name: String) = {
      "name = " + name
    }

    //TODO 3、如果函数体逻辑只有一行代码那么大括号可以省略
    def test3(name: String) = "name = " + name

    //TODO 4、如果函数没有提供参数，那么调用的时候，小括号可以省略
    //      如果函数没有提供参数，那么声明的时候，小括号可以省略
    def test4() = "zhangsan"
  }


}
