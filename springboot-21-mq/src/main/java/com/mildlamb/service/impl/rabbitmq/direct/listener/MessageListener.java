package com.mildlamb.service.impl.rabbitmq.direct.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
public class MessageListener {
    @RabbitListener(queues = {"direct_queue"})
    public void receive(String doneId){
        System.out.println("RabbitMQ已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
    }
}
