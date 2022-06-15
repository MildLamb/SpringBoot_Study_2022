package com.mildlamb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot09HotApplication {

    public static void main(String[] args) {
        // 在系统级别设置关闭热部署
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(Springboot09HotApplication.class, args);
    }

}
