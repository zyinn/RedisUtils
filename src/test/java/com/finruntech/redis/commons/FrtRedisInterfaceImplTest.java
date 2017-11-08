package com.finruntech.redis.commons;

import com.finruntech.redis.service.FrtRedisInterfaceImpl;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * redis interfaceImpl test
 * Created by yinan.zhang on 2017/11/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FrtRedisInterfaceImplTest {
    @Autowired
    private FrtRedisInterfaceImpl redisInterfaceImpl;

    @Test
    public void setRedisStringTest(){
        String key="redisTest";
        String values="fits redis Test";
        redisInterfaceImpl.setup();
        boolean redisString = redisInterfaceImpl.setRedisUtils(key, values, null);
        Assume.assumeTrue(values+":redis存储失败！",redisString);

    }


}
