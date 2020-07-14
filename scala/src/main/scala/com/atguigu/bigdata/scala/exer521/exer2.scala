package com.atguigu.bigdata.scala.exer521

/**
 * @author wx
 * @create 2020-05-21 16:59
 */
object exer2 {
  def main(args: Array[String]): Unit = {
    //2. 如果上一题想将数字A转换为任意数据B（不局限为数字），转换规则自己定义，该如何声明
    def test(i: Int) = {
      if (i < 20) {
        "zhangsan"
      } else {
        i + 20
      }
    }

    println(test(22))

  }
}
