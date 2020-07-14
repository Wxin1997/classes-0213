package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark11_RDD_Operator6 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc")), 3)
rdd.partitionBy(new HashPartitioner(2))

    sc.stop()
  }
}
