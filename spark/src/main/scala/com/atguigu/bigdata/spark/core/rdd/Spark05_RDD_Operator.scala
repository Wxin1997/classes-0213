package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark05_RDD_Operator {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    //从数据集中抽取数据
    //第一个参数表示数据抽取后是否放回
    //第二个参数表示数据抽取的几率(不放回),重复抽取的次数

    val dataRDD: RDD[Int] = rdd.sample(false, 0.5)
    println(dataRDD.collect.mkString(","))

    sc.stop()
  }
}
