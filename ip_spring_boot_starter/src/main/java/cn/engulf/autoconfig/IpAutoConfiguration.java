package cn.engulf.autoconfig;

import cn.engulf.properties.IpCountProperties;
import cn.engulf.properties.TestProperties;
import cn.engulf.service.IpCountService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

// 该类已在spring.factories中声明为bean了，所以可以直接@Bean
@EnableScheduling
//@EnableConfigurationProperties(IpCountProperties.class)
@Import({IpCountProperties.class})
public class IpAutoConfiguration {
    @Bean
    public IpCountService ipCountService(){
        return new IpCountService();
    }
}
