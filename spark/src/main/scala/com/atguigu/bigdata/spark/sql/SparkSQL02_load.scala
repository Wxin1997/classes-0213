package com.atguigu.bigdata.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL02_load {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = new sql.SparkSession.Builder()
      .enableHiveSupport()
      .config(sparkConf).getOrCreate()

   // spark.sql("create table aa(id int)")
   // spark.sql("show tables").show()
    spark.sql("load data local inpath'input/id.txt' into table aa")
    spark.sql("select * from aa").show()

    spark.stop()
  }


}
