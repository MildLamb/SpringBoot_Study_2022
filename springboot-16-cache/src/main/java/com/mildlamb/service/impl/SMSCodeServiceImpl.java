package com.mildlamb.service.impl;

import com.mildlamb.pojo.SMSCode;
import com.mildlamb.service.SMSCodeService;
import com.mildlamb.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtil codeUtil;

    @Override
//    @Cacheable(value = "smsCheckCode",key = "#tele")
    @CachePut(value = "smsCheckCode",key = "#tele")
    public String sendCodeToSMS(String tele) {
        // 返回值就是缓存进去的值
        return CodeUtil.generator(tele);
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        // 取出内存中的验证码与传递过来的验证码进行验证
        String code = smsCode.getCode();
        String cacheCode = codeUtil.getCacheCode(smsCode.getTele());
        return code.equals(cacheCode);
    }

    // 同一个类中调用，没有使用ioc容器中的对象调用方法，注解没有被扫描，使用ioc容器的方法，注解才会被扫描
//    @Cacheable(value = "smsCode",key = "#tele")
//    public String getCacheCode(String tele){
//        return null;
//    }
}
