package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
 * @author wx
 * @create 2020-06-05 20:51
 */
object Spark06_RDD_Test1 {
  def main(args: Array[String]): Unit = {
    //不使用distinct实现数据去重
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 1, 4, 2))
    val groupRDD: RDD[(Int, Iterable[Int])] = rdd.groupBy(data => data)
    val value: RDD[Int] = groupRDD.map(data => data._1)
    println(value.collect().mkString(","))
    sc.stop()
  }
}
