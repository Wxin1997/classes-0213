package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark14_RDD_Operator9 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("a", 1)))

    val groupRDD: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    val wordToCount: RDD[(String, Int)] = groupRDD.map {
      case (word, iter) => (word, iter.size)
    }
    println(wordToCount.collect().mkString(","))

    sc.stop()
  }
}
