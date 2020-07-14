package com.atguigu.bigdata.spark.core.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-01 16:05
 */
object Scala01_WordCount {
  def main(args: Array[String]): Unit = {
    //Spark - WordCount
    //Spark是一个计算框架
    //开发人员是使用Spark框架的API实现计算功能

    //1、准备Spark环境
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("wordCount")

    //2、建立和Spark的链接
    val sc = new SparkContext(sparkConf)

    //3、业务操作
    val fileRDD: RDD[String] = sc.textFile("input")

    val wordRDD: RDD[String] = fileRDD.flatMap(_.split(" "))

    val groupRDD: RDD[(String, Iterable[String])] = wordRDD.groupBy(word => word)

    val mapRDD: RDD[(String, Int)] = groupRDD.map {
      case (word, iter) => {
        (word, iter.size)
      }
    }

    val wordCountArray: Array[(String, Int)] = mapRDD.collect()

    println(wordCountArray.mkString(","))

    //释放连接
    sc.stop()
  }
}
