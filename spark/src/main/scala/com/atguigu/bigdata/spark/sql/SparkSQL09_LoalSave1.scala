package com.atguigu.bigdata.spark.sql

import org.apache.spark.sql.DataFrame
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL09_LoalSave1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()
    //导入隐式转换  spark其实是环境对象的名称
    // 要求这个对象必须使用val声明

    //SparkSQL通用的读取和保存

    spark.sql("select * from json.`input/user.json`").show()

    spark.stop()
  }


}
