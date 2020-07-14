package com.atguigu.summer.framework.util

import java.util.ResourceBundle

/**
 * @author wx
 * @create 2020-05-24 15:31
 */
object PropertiesUtil {
  //绑定配置文件
  val summer: ResourceBundle = ResourceBundle.getBundle("summer")

  def getValue(key: String): String = {
    summer.getString(key)
  }

}
