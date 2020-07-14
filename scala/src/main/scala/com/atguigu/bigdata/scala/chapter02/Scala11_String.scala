package com.atguigu.bigdata.scala.chapter02

/**
 * @author wx
 * @create 2020-05-18 23:48
 */
object Scala11_String {
  def main(args: Array[String]): Unit = {
    val name: String = "zhangsan"
    //val str: String = name.substring(0, 1)
    val age = 20
    //字符串的拼接
    //val hell0 ="Hello " +name
    // println("name=" + name + ",age=" + age)
    //传值字符串
    // println("name= %s,age= %s", name, age)
    //插值字符串
    //println(s"name = $name, age = $age ")

    //JSON => JavaScript Object Notation
    //{"username":"zhangsan","age":20}
    //相同类型的引号不能嵌套使用
    //val json = "{\"username\":\"" + name + "\",\"age\":" + age + "}"
    //println(json)

    //多行字符串 """ ......"""
    //竖线 表示顶格符
    val json =
    s"""
       | {"username":"$name", "age":"$age"}
        """.stripMargin
    println(json)
  }
}
