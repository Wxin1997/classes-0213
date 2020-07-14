package com.atguigu.bigdata.scala.chapter05

/**
 * @author wx
 * @create 2020-05-20 11:09
 */
object Scala04_Function3 {
  def main(args: Array[String]): Unit = {
    // 函数式编程
    //函数参数的个数
    //Scala 提供了参数默认值的语法来解决参数默认值的问题

    //参数默认值：参数声明时，进行初始化
    def regUser(name: String, password: String = "000000"): Unit = {
      println("password = " + password)
    }

    // 如果函数存在默认值参数，调用时可以不用传递，不传参数，会使用默认值
    //regUser("zhangsan")
    // 如果调用函数时，提供了参数值，那么参数默认值不起作用，被覆盖了
    // regUser("lisi", "123123")
    def regUser1(name: String, password: String = "000000", tel: String) {
      println("name = " + name + ", password = " + password + ", tel = " + tel)
    }
    //函数在传递参数的时候，是按照从左到右的方式进行匹配的
    regUser1("zhangsan", "2313214", "24141414215r151")
    regUser1("zhangsan", tel = "1213112311")
  }


}
