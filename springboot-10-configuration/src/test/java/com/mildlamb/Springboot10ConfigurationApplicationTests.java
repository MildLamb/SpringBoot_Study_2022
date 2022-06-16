package com.mildlamb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot10ConfigurationApplicationTests {

    @Value("${server-config.ip-address}")
    private String ip;

    @Value("${datasource.password}")
    private String pwd;

    @Test
    void contextLoads() {
//        System.out.println(ip);
        System.out.println(pwd);
    }

}
