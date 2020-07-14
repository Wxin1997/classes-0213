package com.atguigu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wx
 * @create 2020-07-13 18:18
 */
public class JedisPoolTest {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("hadoop102", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
        jedisPool.close();

    }
}
