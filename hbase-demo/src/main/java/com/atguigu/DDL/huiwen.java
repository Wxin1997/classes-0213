package com.atguigu.DDL;

/**
 * @author wx
 * @create 2020-07-03 13:50
 */
public class huiwen {
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(123);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(int x) {

        StringBuffer sb1 = new StringBuffer(Integer.toString(x));
        StringBuffer sb2 = new StringBuffer(Integer.toString(x));
        System.out.println(sb1.reverse());
        System.out.println(sb2);
        return sb1.reverse().toString().equals(sb2.toString());

    }
}
