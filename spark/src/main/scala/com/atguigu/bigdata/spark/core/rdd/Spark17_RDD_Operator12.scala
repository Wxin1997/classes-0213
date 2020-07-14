package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark17_RDD_Operator12 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    //将分区内相同的key取最大值，分区间相同的key求和
    val rdd = sc.makeRDD(List(("a", 88), ("b", 95), ("a", 91), ("b", 93), ("a", 95), ("b", 98)), 2)

    rdd.combineByKey(v => (v, 1), (t: (Int, Int), v) => {
      (t._1 + v, t._2 + 1)
    }, (t1: (Int, Int), t2: (Int, Int)) => {
      (t1._1 + t2._1, t1._2 + t2._2)
    }).map {
      case (key, (total, cnt)) => (key, total / cnt)
    }.collect.foreach(println)


    sc.stop()
  }
}
