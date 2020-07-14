package com.atguigu.bigdata.spark.core.exer0605

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-06-05 23:04
 */
object wordCount1 {
  case class User(var ss:Int)
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    //    //1516609143867 6 7 64 16
    //    val dataRDD: RDD[String] = sc.textFile("input/agent.log")
    //    dataRDD.map(
    //      line => {
    //        val datas: Array[String] = line.split(" ")
    //        (datas(1) + "-" + datas(4), 1)
    //      }
    //    ) //(省份-商品,1)
    //      .reduceByKey(_ + _) //(省份-商品,sum)
    //      .map {
    //        case (key, sum) => {
    //          val keys: Array[String] = key.split("-")
    //          (keys(0), (keys(1), sum))
    //        }
    //      } //(省份,(商品,sum))
    //      .groupByKey()
    //      .mapValues(
    //        iter => {
    //          val list: List[(String, Int)] = iter.toList
    //          list.sortWith(
    //            (left, right) => left._2 > right._2
    //          ).take(3)
    //        }
    //      ).collect().foreach(println)
    val map1: mutable.Map[String, Long] = mutable.Map(("a", 1), ("b", 2), ("c", 3))
    val map2: mutable.Map[String, Long] = mutable.Map(("a", 2), ("b", 4), ("c", 6),("d", 8))
    val result: mutable.Map[String, Long] = map1.foldLeft(map2) {
      (map, kv) => {
        println(map.mkString(","))
        map(kv._1) = map.getOrElse(kv._1, 0L)
        map
      }
    }
    println(result.mkString(","))

    sc.stop()
  }
}
