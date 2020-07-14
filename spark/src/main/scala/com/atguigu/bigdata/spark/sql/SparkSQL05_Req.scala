package com.atguigu.bigdata.spark.sql

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, sql}

/**
 * @author wx
 * @create 2020-06-10 16:32
 */
object SparkSQL05_Req {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    // TODO 创建环境对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    // builder 构建，创建

    // TODO 访问外置的Hive
    val spark = SparkSession.builder()
      .enableHiveSupport()
      .config(sparkConf).getOrCreate()

    spark.sql("use atguigu200213")

    spark.sql(
      """
        |select
        |   *
        |from (
        |    select
        |        *,
        |        rank() over( partition by area order by clickCount desc ) as rank
        |    from (
        |        select
        |            area,
        |            product_name,
        |            count(*) as clickCount
        |        from (
        |            select
        |               a.*,
        |               c.area,
        |               p.product_name
        |            from user_visit_action a
        |            join city_info c on c.city_id = a.city_id
        |            join product_info p on p.product_id = a.click_product_id
        |            where a.click_product_id > -1
        |        ) t1 group by area, product_name
        |    ) t2
        |) t3
        |where rank <= 3
            """.stripMargin).show

    spark.stop
  }

}
