package com.finruntech.redis.service;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * fits redis pool
 * Created by yinan.zhang on 2017/11/7.
 */
public final class RedisUtil {

    @Value("${server.fits.redis.addr}")
    private static String ADDR;//Redis服务器IP
    @Value("${server.fits.redis.port}")
    private static int PORT; //Redis的端口号
    @Value("${server.fits.redis.auth}")
    private static String AUTH;//访问密码
    //可用连接实例的最大数目，默认值为8
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
    @Value("${server.fits.redis.maxActive}")
    private static int MAX_ACTIVE;//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
    @Value("${server.fits.redis.maxIdle}")
    private static int MAX_IDLE;//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    @Value("${server.fits.redis.maxWait}")
    private static int MAX_WAIT;
    @Value("${server.fits.redis.timeout}")
    private static int TIMEOUT;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMinIdle(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
