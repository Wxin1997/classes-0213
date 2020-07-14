package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark05_RDD_Operator1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 1, 4, 2))


    val dataRDD: RDD[Int] = rdd.distinct()
    val dataRDD1: RDD[Int] = rdd.distinct(2)


    dataRDD.saveAsTextFile("output")
    dataRDD1.saveAsTextFile("output1")


    sc.stop()
  }
}
