package com.mildlamb.service.impl.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig_Topic {
    // 创建存放消息的消息队列
    @Bean
    public Queue topicQueue(){
        return new Queue("topic_queue");
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("topic_queue2");
    }

    // 创建交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    // 完成交换机和消息队列间的绑定
    /*
        *号：用来匹配一个单词，且单词是必须出现的，1个或多个
        #号：用来表示任意数，0个或多个
     */
    @Bean
    public Binding bindingtopic(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.*.id");
    }

    @Bean
    public Binding bindingtopic2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic");
    }
}
