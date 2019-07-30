package com.minip.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    private final static Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        try{
            stringRedisTemplate.opsForValue().set("pandaer", "666-1234", 100000);
            String result = stringRedisTemplate.opsForValue().get("pandaer");
            log.info("Get from redis: " + result);
        }catch(Exception e){
            log.error("Redis operation failed");
            log.error(e.getMessage());
        }
    }
}
