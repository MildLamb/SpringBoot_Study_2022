package com.mildlamb.config;

import com.mildlamb.task.MyQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    // 工作明细
    @Bean
    public JobDetail jobDetail(){
        // 绑定具体的工作
        return JobBuilder.newJob(MyQuartz.class).storeDurably().build();
    }

    // 触发器
    @Bean
    public Trigger jobTrigger(){
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        // 绑定对应的工作明细
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(scheduleBuilder).build();
    }
}
