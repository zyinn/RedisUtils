package com.finruntech.redis.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * fits redis serviceImpl
 * Created by yinan.zhang on 2017/11/7.
 */
@Component
public class FrtRedisInterfaceImpl<T> implements FrtRedisInterface<T> {

    private static Logger logger = Logger.getLogger(FrtRedisInterfaceImpl.class);
    @Value("${server.fits.redis.addr}")
    private  String redisIp; //ip
    @Value("${server.fits.redis.port}")
    private  Integer redisPort; //port
    private final static Integer defaultExpireTime = 86400;

    private Jedis jedis;

    public void setup() {
       //连接redis服务器
       jedis = new Jedis(redisIp, redisPort);
       //权限认证
       //jedis.auth("admin");
    }

    void setExpireTime(String key, Integer time) {
        time = time!=null ? time : defaultExpireTime;
        jedis.expire(key,time);
    }

    @Override
    public  boolean setRedisUtils(String key,T value,Integer time) {
        try {
            if(value instanceof String){
                jedis.set(key, (String) value);
                setExpireTime(key, time);
            }else if(value instanceof List){
                List<T> values = (List<T>) value;
                values.forEach(date -> {
                    jedis.lpush(key, date.toString());
                    setExpireTime(key, time);
                });
            }else if(value instanceof Map){
                jedis.hmset(key, (Map<String, String>) value);
                setExpireTime(key, time);
            }else if(value instanceof Set){
                Set<T> values = (Set<T>) value;
                values.forEach(tt ->{
                    jedis.sadd(key,tt.toString());
                    setExpireTime(key, time);
                });
            }else{
                logger.error(value+":redis存储失败,类型不匹配！");
            }
            return true;
        } catch (Exception e) {
            logger.error("setRedisUtils存储失败!");
            return false;
        }
    }

    @Override
    public void setRedisKey(){
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }

    @Override
    public List<T> getRedisList(String key){
        // 获取存储的数据并输出
        Long llen = jedis.llen(key);
        if(llen>0){
            List<String> list = jedis.lrange(key, 0 ,llen);
            return (List<T>) list;
        }
        return new ArrayList<>();
    }
    /**
     * redis操作Map
     */
    public void setMapRedisTest(String key ,Map<String, String> map) {
        //连接本地的 Redis 服务
        //Jedis jedis = new Jedis(redisIp);
        //-----添加数据----------
        jedis.hmset(key, map);

        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        // List<String> rsmap = jedis.hmget(key, "name", "age", "qq");
        // System.out.println(rsmap);
        //删除map中的某个键值
        //jedis.hdel(key, "age");
        //System.out.println(jedis.hmget(key, "age")); //因为删除了，所以返回的是null
        // System.out.println(jedis.hlen(key)); //返回key为user的键中存放的值的个数2
        // System.out.println(jedis.exists(key));//是否存在key为user的记录 返回true
        //System.out.println(jedis.hkeys(key));//返回map对象中的所有key
        // System.out.println(jedis.hvals(key));//返回map对象中的所有value
        Iterator<String> iter = jedis.hkeys(key).iterator();
        while (iter.hasNext()) {
            String redisKey = iter.next();
            System.out.println(redisKey + ":" + jedis.hmget(key, redisKey));
        }
    }
    public List<T> getMapRedis(String redisKey ,String key) {
        try {
            //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
            //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
            List<String> rsmap = jedis.hmget(redisKey, key);
            return (List<T>) rsmap;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }
    public List<T> getMapRedisHvals(String redisKey) {
        try {
            return (List<T>) jedis.hvals(redisKey);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    public Map<String,T> getMapRedisHkeys(String redisKey) {
        Map<String,T> map = new HashMap<>();
        try {
            Iterator<String> iter = jedis.hkeys(redisKey).iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                List<String> hmget = jedis.hmget(redisKey, key);
                System.out.println(redisKey + ":" + jedis.hmget(redisKey, key));
                map.put(key, (T) hmget);
            }
            return map;
        } catch (Exception e) {
            return map;
        }
    }

}

