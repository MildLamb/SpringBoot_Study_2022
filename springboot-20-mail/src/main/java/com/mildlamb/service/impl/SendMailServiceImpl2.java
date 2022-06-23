package com.mildlamb.service.impl;

import com.mildlamb.service.SendMailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SendMailServiceImpl2 implements SendMailService {

    @Resource
    private JavaMailSender mailSender;

    // 发送人
    private String mailFrom = "xxx@qq.com";
    // 接收人
    private String mailTo = "xxx@qq.com";
    // 标题
    private String mailTitle = "千青灵花王玉瓷间";
    // 正文
    private String mailText = "<a href='https://www.bilibili.com/'>点我有惊喜</a>";

    @Override
    public void sendMail(){
        try {
            // 创建邮件消息容器(高端版)
            MimeMessage message = mailSender.createMimeMessage();
            // 设置邮件消息(第二个参数表示允许多部件，附件也是部件的一种)
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(mailFrom+"(温柔小羊)");
            messageHelper.setTo(mailTo);
            messageHelper.setSubject(mailTitle);
            // 第二个参数表示是否支持html解析
            messageHelper.setText(mailText,true);

            // 添加附件
            File file = new File("C:\\Users\\MildLamb\\Pictures\\Kindred_83776140.png");
            messageHelper.addAttachment("小羔羊.png",file);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
