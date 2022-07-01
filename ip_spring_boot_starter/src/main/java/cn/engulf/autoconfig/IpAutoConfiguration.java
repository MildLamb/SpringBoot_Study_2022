package cn.engulf.autoconfig;

import cn.engulf.service.IpCountService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

// 该类已在spring.factories中声明为bean了，所以可以直接@Bean
@EnableScheduling
public class IpAutoConfiguration {
    @Bean
    public IpCountService ipCountService(){
        return new IpCountService();
    }
}
