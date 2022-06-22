package com.mildlamb;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// jetcacahe启用缓存的主开关
@EnableCreateCacheAnnotation
// 开启方法缓存注解的开关
@EnableMethodCache(basePackages = {"com.mildlamb"})
public class Springboot17JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot17JetcacheApplication.class, args);
    }

}
