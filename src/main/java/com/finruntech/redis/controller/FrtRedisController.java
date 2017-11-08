package com.finruntech.redis.controller;

import com.finruntech.redis.model.FrtRedisEntity;
import com.finruntech.redis.service.FrtRedisInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yinan.zhang on 2017/11/8.
 */
@CrossOrigin
@RestController
public class FrtRedisController {

    @Autowired
    private FrtRedisInterface frtRedisService;

    @PostMapping("redis")
    public ResponseEntity<?> saveFrtRedisEntity(@RequestBody FrtRedisEntity entity) {
        boolean redisString = frtRedisService.setRedisString(entity.getKey(), (String) entity.getValue(), entity.getExpireTime());
        return ResponseEntity.ok().body(redisString ? 1 : 0);
    }
}
