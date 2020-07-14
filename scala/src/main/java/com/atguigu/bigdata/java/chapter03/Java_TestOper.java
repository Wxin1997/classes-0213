package com.atguigu.bigdata.java.chapter03;

import scala.math.Ordering;

/**
 * @author wx
 * @create 2020-05-19 19:25
 */
public class Java_TestOper {
    public static void main(String[] args) {
        String a = new String("abc");
        String b = new String("abc");
        System.out.println(a == b);     // false
        System.out.println(a.equals(b));//true
    }
}
