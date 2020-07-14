package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark18_RDD_Req {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)

    val dataRDD: RDD[String] = sc.textFile("input/agent.log")

    val mapRDD: RDD[(String, Int)] = dataRDD.map(
      line => {
        val datas: Array[String] = line.split(" ")
        (datas(1) + "-" + datas(4), 1)
      }
    )

    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)
    val mapRDD1: RDD[(String, (String, Int))] = reduceRDD.map {
      case (key, sum) => {
        val keys: Array[String] = key.split("-")
        (keys(0), (keys(1), sum))
      }
    }

    val groupRDD: RDD[(String, Iterable[(String, Int)])] = mapRDD1.groupByKey()

    val sortRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues(iter => {
      val list: List[(String, Int)] = iter.toList
      list.sortWith(
        (left, right) => left._2 > right._2
      ).take(3)
    })

    sortRDD.collect().foreach(println)

    sc.stop()
  }
}
