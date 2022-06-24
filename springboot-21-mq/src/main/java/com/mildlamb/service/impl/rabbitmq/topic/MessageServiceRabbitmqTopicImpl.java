package com.mildlamb.service.impl.rabbitmq.topic;

import com.mildlamb.service.MessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceRabbitmqTopicImpl implements MessageService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送短信的订单已纳入处理队列(rabbitmq topic)，id：" + id);
        // 交换机名称     routing key     发送的消息
        amqpTemplate.convertAndSend("topicExchange","topic.order.id",id);
    }

    @Override
    public String doMessage() {
        return null;
    }
}
