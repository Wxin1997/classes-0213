package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark13_RDD_Operator8 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("a", 1)))

    //根据数据的key进行分组，对value进行聚合
    println(rdd.reduceByKey(_ + _).collect().mkString(","))
    sc.stop()
  }
}
