package com.atguigu.bigdata.spark.core.operator.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-06 11:44
 */
object Spark04_Operator_Action3 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

    rdd.saveAsTextFile("output")
    rdd.saveAsObjectFile("output1")
    rdd.map((_,1)).saveAsSequenceFile("output2")


    sc.stop()
  }
}
