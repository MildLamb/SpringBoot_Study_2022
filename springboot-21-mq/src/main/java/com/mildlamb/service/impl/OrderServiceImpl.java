package com.mildlamb.service.impl;

import com.mildlamb.service.MessageService;
import com.mildlamb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MessageService messageService;

    @Override
    public void createOrder(String id) {
        // 假装处理了一堆事情
        System.out.println("订单处理开始");
        // 短信消息处理
        messageService.sendMessage(id);

        System.out.println("订单处理结束");
    }
}
