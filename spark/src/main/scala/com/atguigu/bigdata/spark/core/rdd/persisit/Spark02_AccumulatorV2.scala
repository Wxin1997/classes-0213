package com.atguigu.bigdata.spark.core.rdd.persisit

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.util.AccumulatorV2

import scala.collection.mutable

/**
 * @author wx
 * @create 2020-06-08 14:58
 */
object Spark02_AccumulatorV2 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("File - RDD")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.makeRDD(List("hello Scala", "hello Spark"))

    val acc = new WordCountAccumulator
    sc.register(acc)

    rdd.flatMap(_.split(" ")).foreach(
      word => acc.add(word)
    )

    println(acc.value)

    sc.stop()
  }

  class WordCountAccumulator extends AccumulatorV2[String, mutable.Map[String, Long]] {
    var wordCountMap: mutable.Map[String, Long] = mutable.Map()

    override def isZero: Boolean = {
      wordCountMap.isEmpty
    }

    override def copy(): AccumulatorV2[String, mutable.Map[String, Long]] = {
      new WordCountAccumulator
    }

    override def reset(): Unit = {
      wordCountMap.clear()
    }

    override def add(word: String): Unit = {
      wordCountMap(word) = wordCountMap.getOrElse(word, 0L) + 1L
    }

    override def merge(other: AccumulatorV2[String, mutable.Map[String, Long]]): Unit = {
      val map1 = wordCountMap
      val map2 = other.value
      wordCountMap = map1.foldLeft(map2) {
        (map, kv) => {
          map(kv._1) = map.getOrElse(kv._1, 0L) + kv._2
          map
        }
      }
    }

    override def value: mutable.Map[String, Long] = wordCountMap
  }

}
