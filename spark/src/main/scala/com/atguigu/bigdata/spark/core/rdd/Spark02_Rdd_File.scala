package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-02 21:36
 */
object Spark02_Rdd_File {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)
    //从磁盘中创建RDD
    // val fileRDD: RDD[String] = sc.textFile("input")
    //val fileRDD: RDD[String] = sc.textFile("input/word.txt")
    val fileRDD: RDD[String] = sc.textFile("input/word*.txt")
    println(fileRDD.collect().mkString(","))

    sc.stop()
  }
}
