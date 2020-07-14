package com.atguigu.bigdata.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL01_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()
    //导入隐式转换  spark其实是环境对象的名称
    // 要求这个对象必须使用val声明
    import spark.implicits._

    val jsonDF: DataFrame = spark.read.json("input/user.json")

    jsonDF.createOrReplaceTempView("user")
    spark.sql("select * from user").show()

    jsonDF.select("name", "age").show()
    jsonDF.select('name, 'age).show

    val rdd = spark.sparkContext.makeRDD(List((1, "zhangsan", 30),
      (2, "lisi", 20),
      (3, "wangwu", 40),
    ))
    val df: DataFrame = rdd.toDF("id", "name", "age")
    val dfToRDD: RDD[Row] = df.rdd

    val userRDD: RDD[User] = rdd.map {
      case (id, name, age) => User(id, name, age)
    }

    val userDS: Dataset[User] = userRDD.toDS()
    val dsToRDD: RDD[User] = userDS.rdd

    val dfTODS: Dataset[User] = df.as[User]
    val dsTODF: DataFrame = dfTODS   .toDF()
    spark.stop()
  }

  case class User(id: Int, name: String, age: Int)

}
