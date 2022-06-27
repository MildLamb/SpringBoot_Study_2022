package com.mildlamb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SSMPApplication {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(SSMPApplication.class, args);

        // 可以在启动boot程序时断开读取外部临时属性配置的对应入口，也就是去掉读取外部临时属性的形参
//        SpringApplication.run(SSMPApplication.class);
    }

}
