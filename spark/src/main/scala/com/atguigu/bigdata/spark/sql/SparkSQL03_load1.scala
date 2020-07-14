package com.atguigu.bigdata.spark.sql

import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL03_load1 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark = new sql.SparkSession.Builder()
      .enableHiveSupport()
      .config(sparkConf).getOrCreate()

   // spark.sql("create table aa(id int)")
   // spark.sql("show tables").show()



    spark.sql("show databases").show()

    spark.stop()
  }


}
