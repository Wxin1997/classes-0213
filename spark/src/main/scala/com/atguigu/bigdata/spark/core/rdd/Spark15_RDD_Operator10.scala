package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark15_RDD_Operator10 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    //将分区内相同的key取最大值，分区间相同的key求和
    val rdd = sc.makeRDD(List(("a", 1), ("a", 2), ("c", 3), ("b", 4), ("c", 5), ("c", 6)), 2)

    val result: RDD[(String, Int)] = rdd.aggregateByKey(0)((x, y) => math.max(x, y), (x, y) => x + y)
    println(result.collect().mkString(","))
    sc.stop()
  }
}
