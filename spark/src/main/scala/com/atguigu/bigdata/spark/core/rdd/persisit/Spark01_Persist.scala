package com.atguigu.bigdata.spark.core.rdd.persisit

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-08 10:41
 */
object Spark01_Persist {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    val mapRDD: RDD[(Int, Int)] = rdd.map(
      num => {
        println("map....")
        (num, 1)
      }
    )

    // mapRDD.cache()

    println(mapRDD.collect().mkString(","))
    println("***************")

    mapRDD.saveAsTextFile("output")

    sc.stop()

  }
}
