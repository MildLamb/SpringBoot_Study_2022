package com.mildlamb;

import com.mildlamb.pojo.MyDataSource;
import com.mildlamb.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot02BaseConfigurationApplicationTests {

    @Autowired
    private Role role;

    @Value("${role.tempString}")
    private String tempString;

    @Autowired
    private MyDataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(role);
        System.out.println("==========================");
        System.out.println(tempString);
        System.out.println("==========================");
        System.out.println(dataSource);
    }

}
