package com.atguigu.bigdata.spark.sql


import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL07_UDAF_Class {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SQL")
    val spark = new sql.SparkSession.Builder().config(sparkConf).getOrCreate()
    //导入隐式转换  spark其实是环境对象的名称
    // 要求这个对象必须使用val声明
    import spark.implicits._


    val rdd = spark.sparkContext.makeRDD(List(
      (1, "zhangsan", 30L),
      (2, "lisi", 20L),
      (3, "wangwu", 40L),
    ))

    val df: DataFrame = rdd.toDF("id", "name", "age")
    val ds: Dataset[User] = df.as[User]

    //创建UDAF函数
    val udaf = new MyAvgUDAF

    // 因为聚合函数是强类型，sql没有类型的概念，所以无法使用
    // 将聚合幻术转换为查询的列让DataSet查询

    ds.select( udaf.toColumn).show()




    spark.stop()
  }

  // 自定义聚合函数  -  强类型
  // 1、继承Aggregator 定义泛型
  //    IN
  //    BUF
  //    OUT

  case class User(id: Int, name: String, age: Long)

  case class AvgBuffer(var totalage: Long, var count: Long)

  class MyAvgUDAF extends Aggregator[User, AvgBuffer, Long] {
    // 缓冲区的初始值
    override def zero: AvgBuffer = {
      AvgBuffer(0L, 0L)
    }

    // 聚合数据
    override def reduce(b: AvgBuffer, a: User): AvgBuffer = {
      b.totalage += a.age
      b.count += 1L
      b
    }

    // 合并缓冲区
    override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
      b1.totalage += b2.totalage
      b1.count += b2.count
      b1
    }

    // 计算函数的结果
    override def finish(reduction: AvgBuffer): Long = {
      reduction.totalage / reduction.count
    }

    //
    override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }


}
