package com.atguigu.bigdata.spark.streaming.req

/**
 * @author wx
 * @create 2020-06-20 10:48
 */
package object bean {
  case class Ad_Click_Log(ts:String,area:String,city:String,userid:String,adid:String)
}
