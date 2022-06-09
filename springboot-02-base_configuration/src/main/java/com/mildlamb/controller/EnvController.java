package com.mildlamb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
    @Autowired
    private Environment environment;

    @GetMapping("/env")
    public String getEnv(){
        System.out.println(environment);
        return environment.getProperty("role.name");
    }
}
