package com.mildlamb.service.impl;

import com.mildlamb.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Service
public class SendMailServiceImpl implements SendMailService {

    @Resource
    private JavaMailSender mailSender;

    // 发送人
    private String mailFrom = "***@qq.com";
    // 接收人
    private String mailTo = "***@qq.com";
    // 标题
    private String mailTitle = "千青灵花王玉瓷间";
    // 正文
//    private String mailText = "如果天空不亮，那就摸黑生活;如果发出声音是危险的，那就保持沉默；如果" +
//            "自觉无力发光，那就不必去照亮他人。但是，不要习惯了黑暗就为黑暗辩护，不要为了自己的苟且洋洋得意，" +
//            "不要嘲讽那些比自己更勇敢，更有热量的人们。我们可以卑微如尘土，但不可以扭曲如蛆虫。";

    private String mailText = "拥抱生命便意味着接受死亡";

    @Override
    public void sendMail() {
        // 创建邮件消息容器
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件消息
        message.setFrom(mailFrom+"(MildLamb)");
        message.setTo(mailTo);
        message.setSubject(mailTitle);
        message.setText(mailText);

        mailSender.send(message);
    }
}
