package com.atguigu.bigdata.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL06_Test1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()
    //导入隐式转换  spark其实是环境对象的名称
    // 要求这个对象必须使用val声明
    import spark.implicits._


    val rdd = spark.sparkContext.makeRDD(List((1, "zhangsan", 30),
      (2, "lisi", 20),
      (3, "wangwu", 40),
    ))

    val df: DataFrame = rdd.toDF("id", "name", "age")
    df.createOrReplaceTempView("user")


    //
    //    val userRDD: RDD[User] = rdd.map {
    //      case (id, name, age) => User(id, name, age)
    //    }
    //
    //
    //    val userDS: Dataset[User] = userRDD.toDS()
    //    val newDS: Dataset[User] = userDS.map(user => {
    //      User(user.id, "name:" + user.name, user.age)
    //    })
    //
    //    newDS.show()

    //使用 自定义函数在SQL中完成数据的转换
    spark.udf.register("addName", (x: String) => "Name:" + x)

    spark.sql("select addName(name) from user").show()


    spark.stop()
  }

  case class User(id: Int, name: String, age: Int)

}
