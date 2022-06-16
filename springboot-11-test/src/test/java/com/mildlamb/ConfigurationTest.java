package com.mildlamb;

import com.mildlamb.config.MsgConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({MsgConfig.class})
public class ConfigurationTest {
    @Autowired
    private String msg;

    @Test
    void test(){
        System.out.println(msg);
    }
}
