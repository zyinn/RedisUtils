package com.finruntech.redis.service;

import java.util.List;

/**
 * redis Interface
 * Created by yinan.zhang on 2017/11/7.
 */
public interface FrtRedisInterface<T> {

    boolean setRedisUtils(String key,T value,Integer time);



    /**
     * key:stock_key
     */
    void setRedisKey();


    List<T> getRedisList(String key);
}
