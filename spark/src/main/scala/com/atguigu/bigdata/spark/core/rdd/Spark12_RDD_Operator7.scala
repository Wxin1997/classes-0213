package com.atguigu.bigdata.spark.core.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-05 20:38
 */
object Spark12_RDD_Operator7 {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    //自定义分区器
    val rdd = sc.makeRDD(List(("cba", "消息1"), ("cba", "消息2"), ("cba", "消息3"),
      ("nba", "消息4"), ("wnba", "消息5"), ("nba", "消息6")))

    val rdd1: RDD[(String, String)] = rdd.partitionBy(new MyPartitioner(3))
    val rdd2: RDD[(Int, (String, String))] = rdd1.mapPartitionsWithIndex(
      (index, datas) => {
        datas.map(
          data => (index, data)
        )
      }
    )

    rdd2.collect().foreach(println)

    sc.stop()
  }

  class MyPartitioner(num: Int) extends Partitioner {
    override def numPartitions: Int = {
      num
    }

    override def getPartition(key: Any): Int = {
      key match {
        case "cba" => 0
        case _ => 1
      }
    }

  }

}