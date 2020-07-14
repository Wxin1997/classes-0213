package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark10_RDD_Operator5 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List(
      1, 2, 3, 4, 1, 2
    ), 2)
    //println(rdd.sortBy(data => data).collect.mkString(","))
    println(rdd.sortBy(data => data, false).collect.mkString(","))


    sc.stop()
  }
}
