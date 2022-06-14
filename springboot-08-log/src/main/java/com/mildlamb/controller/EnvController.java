package com.mildlamb.controller;

import com.mildlamb.utils.BaseLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EnvController implements BaseLogger {
    // 创建记录日志的对象
//    private static final Logger log = BaseLogger.getLogger(EnvController.class);


    @GetMapping("/env")
    public String getEnv(){

        // 级别由低到高
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");

        return "hello";
    }
}
