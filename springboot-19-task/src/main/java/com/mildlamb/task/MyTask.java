package com.mildlamb.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {
    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask(){
        System.out.println(Thread.currentThread().getName() + " : 千青灵花王玉瓷间");
    }
}
