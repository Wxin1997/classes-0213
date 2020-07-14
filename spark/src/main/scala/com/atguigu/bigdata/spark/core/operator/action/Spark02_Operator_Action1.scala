package com.atguigu.bigdata.spark.core.operator.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-06 11:44
 */
object Spark02_Operator_Action1 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(2, 1, 3, 4))
    //val i: Int = rdd.reduce(_ + _)

    //采取数据

    //1,2,3,4
    val array: Array[Int] = rdd.takeOrdered(3)
    println(array.mkString(","))

    sc.stop()
  }
}
