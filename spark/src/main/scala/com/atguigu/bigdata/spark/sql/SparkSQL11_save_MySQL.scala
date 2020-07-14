package com.atguigu.bigdata.spark.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL11_save_MySQL {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()

    val frame: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://hadoop102:3306/spark-sql")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user")
      .load()

    frame.write.format("jdbc")
      .option("url", "jdbc:mysql://hadoop102:3306/spark-sql")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "123456")
      .option("dbtable", "user1")
      .save()


    spark.stop()
  }


}
