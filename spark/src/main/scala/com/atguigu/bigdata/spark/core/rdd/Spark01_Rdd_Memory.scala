package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-02 21:36
 */
object Spark01_Rdd_Memory {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)
    //从内存中创建RDD

    //1、并行
    val list = List("张三", "李四","王五", "赵六")
    val rdd = sc.parallelize(list)
    println(rdd.collect().mkString(","))

    val rdd1 = sc.makeRDD(list)
    println(rdd1.collect().mkString(","))
    sc.stop()
  }
}
