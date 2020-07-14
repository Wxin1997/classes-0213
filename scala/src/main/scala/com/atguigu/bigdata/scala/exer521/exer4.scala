package com.atguigu.bigdata.scala.exer521

/**
 * @author wx
 * @create 2020-05-21 16:59
 */
object exer4 {
  def main(args: Array[String]): Unit = {
    //4. 如果设计一个用于过滤的函数，你会如何做？
    //要求：对传递的包含多个单词的字符串参数,根据指定的规则对word进行过滤
    //例子："hello world scala spark" => 首写字母为s => "scala, spark"

    def test(s: String) = {
      var result = ""
      val strings = s.split(" ")

      for (i <- 0 to strings.length - 1) {

        if (i < strings.length - 1) {

          if (strings(i).startsWith("s")) {
            result += strings(i)
            result += ", "
          }
        } else {
          if (strings(i).startsWith("s")) {
            result += strings(i)
          }
        }
      }
      result
    }

    println(test("hello world scala spark"))
  }
}
