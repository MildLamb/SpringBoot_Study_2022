package com.mildlamb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// properties属性可以为当前测试用例添加临时的属性配置
//@SpringBootTest(properties = {"test.prop = kindred"})

@SpringBootTest(properties = {"test.prop = Gnar"} , args = {"--test.prop = Kindred"})
class PropertiesAndArgsTest {

    @Value("${test.prop}")
    private String testVal;

    @Test
    void testProperties() {
        System.out.println(testVal);
    }

}
