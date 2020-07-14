package com.atguigu.bigdata.spark.core.operator.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author wx
 * @create 2020-06-06 11:44
 */
object Spark01_Operator_Action {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("File-RDD")
    val sc = new SparkContext(sparkConf)
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
    //val i: Int = rdd.reduce(_ + _)

    //采取数据
    //collect方法会将所有分区计算的结果拉取到当前节点的内存当中，可能会出现按内存溢出
    //val array: Array[Int] = rdd.collect()

    //count

    val cnt: Long = rdd.count()
    println(cnt)
    // println(i)

    val f: Int = rdd.first()

    val subArray: Array[Int] = rdd.take(3)
    sc.stop()
  }
}
