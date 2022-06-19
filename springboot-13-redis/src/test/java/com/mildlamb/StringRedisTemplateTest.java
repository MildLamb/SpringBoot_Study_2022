package com.mildlamb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class StringRedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void getRedis(){
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        String age = opsForValue.get("roleName");
        System.out.println(age);
    }

    @Test
    void setRedis(){
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("roleName","Kindred");
    }
}
