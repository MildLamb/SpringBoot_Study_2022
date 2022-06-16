package com.mildlamb;

import com.alibaba.druid.pool.DruidDataSource;
import com.mildlamb.pojo.ServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// @EnableConfigurationProperties会自动将数组参数中的类创建到spring容器中
@EnableConfigurationProperties({ServerConfig.class})
public class Springboot10ConfigurationApplication {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.driver");
        // 从配置文件中读取值，进行初始化配置
        return ds;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Springboot10ConfigurationApplication.class, args);
        ServerConfig bean = ctx.getBean(ServerConfig.class);
        System.out.println(bean);
//        DruidDataSource ds = ctx.getBean(DruidDataSource.class);
//        System.out.println(ds);
//        System.out.println(ds.getDriverClassName());
    }

}
