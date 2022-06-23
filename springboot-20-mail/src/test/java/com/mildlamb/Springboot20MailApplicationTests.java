package com.mildlamb;

import com.mildlamb.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot20MailApplicationTests {

    @Autowired
    private SendMailService mailService;

    @Test
    void contextLoads() {
        mailService.sendMail();
    }

}
