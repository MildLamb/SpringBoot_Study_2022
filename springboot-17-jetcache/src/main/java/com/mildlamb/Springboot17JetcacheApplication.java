package com.mildlamb;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// jetcacahe启用缓存的主开关
@EnableCreateCacheAnnotation
public class Springboot17JetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot17JetcacheApplication.class, args);
    }

}
