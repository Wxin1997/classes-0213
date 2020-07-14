package com.atguigu.bigdata.scala.chapter06


import java.sql.{Date => _, Array => _, _}
import java.util._

/**
 * @author wx
 * @create 2020-05-23 11:33
 */
object Scala04_Import {
  def main(args: Array[String]): Unit = {
    //Scala - 面向对象 - import

    //1、导类，不是导包
    //2、import static 静态导入，导入类的静态属性和静态方法
    //java语法中import操作功能比较单一。但是不能舍弃
    //scala语言在java语法基础上进行扩展。

    // 1、import 可以声明在任意的位置

    //2、 java中默认导入的类是java.lang包中得类
    //    scala中默认导入的类
    //     2.1 java.lang中的类
    //     2.2 scala中的类
    //     2.3 Predef

    //3、 Scala中的import可以导包

    //4、 导入一个包中的所有的类

    //5、导类 可以在一行中导入同一个包的多个类
    //    import java.util.{ArrayList, HashMap}
    //    //    new ArrayList()
    //    //    new HashMap()

    //6、使用import关键字将包中某些包隐藏掉

    type UtilDate = java.util.Date
    val date = new UtilDate
  }
}
