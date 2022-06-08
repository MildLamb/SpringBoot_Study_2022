package com.mildlamb;

import com.mildlamb.controller.HelloController;
import com.mildlamb.pojo.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Springboot_01_StartApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Springboot_01_StartApplication.class, args);
        HelloController bean = ctx.getBean(HelloController.class);
        System.out.println("bean =========> " + bean);
        Role role = ctx.getBean(Role.class);
        System.out.println(role);
    }

}
