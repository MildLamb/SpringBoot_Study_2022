package com.mildlamb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
class Springboot13RedisApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void getRedis() {
        // 声明要操作那种数据类型
        ValueOperations ops = redisTemplate.opsForValue();
        Object age = ops.get("age");
        System.out.println(age);
    }

    @Test
    void setRedis(){
        // 声明要操作那种数据类型
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("age",1500);
    }

}
