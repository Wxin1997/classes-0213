package com.atguigu.bigdata.scala.exer521

/**
 * @author wx
 * @create 2020-05-21 16:59
 */
object exer3 {
  def main(args: Array[String]): Unit = {
    //3. 如果函数调用：  test(10,20,c)的计算结果由参数c来决定，这个参数c你会如何声明
    def test(a: Int, b: Int, f: (Int, Int) => Int) = f(a, b)

    println(test(10, 20, (_ + _)))
  }
}
