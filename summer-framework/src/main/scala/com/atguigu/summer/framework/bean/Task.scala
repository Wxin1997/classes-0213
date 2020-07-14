package com.atguigu.summer.framework.bean

/**
 * @author wx
 * @create 2020-05-24 15:52
 */
class Task extends Serializable {
  var data: Int = 0
  var logic: Int => Int = null

  def compute() = {
    logic(data)
  }

}
