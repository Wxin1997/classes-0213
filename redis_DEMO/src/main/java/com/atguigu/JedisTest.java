package com.atguigu;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * @author wx
 * @create 2020-07-13 18:13
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("hadoop102", 6379);
        String ping = jedis.ping();
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println(ping);
        jedis.close();
    }
}
