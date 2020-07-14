package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-02 21:36
 */
object Spark03_Rdd_Memory_Par {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[4]").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)
    //从内存中创建RDD

    //1、并行
    //makeRDDd的第一个参数：数据源
    //makeRDDd的第二个参数：默认并行度

    val rdd: RDD[Int] = sc.parallelize(List(1, 2, 3, 4))
    //println(rdd.collect().mkString(","))

    rdd.saveAsTextFile("output")

    sc.stop()
  }
}
