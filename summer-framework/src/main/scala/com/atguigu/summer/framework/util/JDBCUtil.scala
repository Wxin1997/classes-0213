package com.atguigu.summer.framework.util

import java.util

import com.alibaba.druid.pool.DruidDataSourceFactory
import javax.sql.DataSource

/**
 * @author wx
 * @create 2020-06-20 10:54
 */
object JDBCUtil {

  def init(): DataSource = {

    val paramMap = new util.HashMap[String,String]()
    paramMap.put("driverClassName",PropertiesUtil.getValue("jdbc.driver.name"))
    paramMap.put("url",PropertiesUtil.getValue("jdbc.url"))
    paramMap.put("username",PropertiesUtil.getValue("jdbc.user"))
    paramMap.put("password",PropertiesUtil.getValue("jdbc.password"))
    paramMap.put("maxActive",PropertiesUtil.getValue("jdbc.datasource.size"))

    //使用Druid连接池
    DruidDataSourceFactory.createDataSource(paramMap)


  }

  // 创建连接池对象
  var dataSource :DataSource =init()

  // 从连接池中获取连接对象
  def getConnection()={
    dataSource.getConnection
  }

}
