package com.finruntech.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yinan.zhang on 2017/11/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrtRedisEntity<T> {
    private String key;
    private T value;
    private Integer expireTime;
}
