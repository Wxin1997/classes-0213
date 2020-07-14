package com.atguigu.bigdata.java.chapter01;

import java.lang.reflect.Field;

/**
 * @author wx
 * @create 2020-05-18 22:53
 */
public class Test03_String {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //final 修饰变量， 一旦初始化后，值（内存地址）无法发生改变
        //String类一旦初始化之后，没有提供任何的方法来改变字符串的内容
        //而是提供了产生新的字符串的方法
        String s = " a b ";
        //内存没有发生改变
        // s.trim();//去掉的首尾的半角空格
        //  System.out.println("!" + s + "!");
        Class<? extends String> aClass = s.getClass();
        Field f = aClass.getDeclaredField("value");

        //获取属性的值
        f.setAccessible(true);
        char[] chs = (char[]) f.get(s);
        chs[2] = 'd';
        System.out.println(s);
    }
}
