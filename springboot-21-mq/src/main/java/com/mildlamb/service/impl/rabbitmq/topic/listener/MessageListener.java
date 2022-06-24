package com.mildlamb.service.impl.rabbitmq.topic.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @RabbitListener(queues = {"topic_queue"})
    public void receive(String doneId){
        System.out.println("(Topic 1)RabbitMQ已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
    }

    @RabbitListener(queues = {"topic_queue2"})
    public void receive2(String doneId){
        System.out.println("(Topic 2)RabbitMQ已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
    }
}
