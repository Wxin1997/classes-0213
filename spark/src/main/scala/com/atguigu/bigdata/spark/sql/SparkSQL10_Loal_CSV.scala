package com.atguigu.bigdata.spark.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL10_Loal_CSV {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()

    import spark.implicits._

    val frame: DataFrame = spark.read.format("csv").option("sep", ";")
      .option("inferSchema", "true").option("header", "true")
      .load("input/user.csv")
    frame.show()

    spark.stop()
  }


}
