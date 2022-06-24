package com.mildlamb.service.impl.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

//@Component
public class MessageListener {
    // 监听消息队列中是否有消息，有消息就进行处理

    // 指定监听哪个消息队列
    @JmsListener(destination = "order.queue.id")

    // 将这次消息处理后的返回值发送到新的队列中
    @SendTo("order.other.queue.id")
    public String receive(String doneId){
        System.out.println("已监听到消息队列中的消息，下面开始处理，已完成短信发送业务，id：" + doneId);
        return "new:" + doneId;
    }
}
