package com.mildlamb.service.impl.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitmqConfig_Direct {
    // 创建存放消息的消息队列
    @Bean
    public Queue directQueue(){
        // durable：是否持久化，默认true
        // exclusive：是否当前连接专用，默认false，即连接关闭后队列即被删除
        // autoDelete：是否自动删除，当生产者和消费者不再使用此队列时，自动删除
        return new Queue("direct_queue",true,false,false);
    }

    // 创建交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    // 完成交换机和消息队列间的绑定
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }
}
