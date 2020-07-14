package com.atguigu.bigdata.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL08_LoalSave {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()
    //导入隐式转换  spark其实是环境对象的名称
    // 要求这个对象必须使用val声明
    import spark.implicits._

    //SparkSQL通用的读取和保存

    //通用的读取
    //val frame: DataFrame = spark.read.load("input/users.parquet")
    //frame.show()
    val df: DataFrame = spark.read.format("json").load("input/user.json")

    //通用读取和保存都是parquet
    //如果路径已经存在，哪么执行保存操作会发生错误。
    //如果飞的想要在已经存在路径的情况下，保存数据们可以使用保存模式

    //df.write.format("json").save("output")
     df.write.mode("overwrite").format("json").save("output")

    spark.stop()
  }


}
