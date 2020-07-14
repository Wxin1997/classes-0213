package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-02 21:36
 */
object Spark03_Rdd_Memory_PartitonData {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")
    val sc = new SparkContext(sparkConf)
    //从内存中创建RDD
    val rdd = sc.makeRDD(List(1, 2, 3, 4), 2)
    rdd.saveAsTextFile("output")

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4), 4)
    rdd1.saveAsTextFile("output1")

    val rdd2 = sc.makeRDD(List(1, 2, 3, 3), 4)
    rdd2.saveAsTextFile("output2")

    sc.stop()
  }
}
