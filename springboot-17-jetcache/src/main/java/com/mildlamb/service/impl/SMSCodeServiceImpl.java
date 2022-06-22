package com.mildlamb.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.mildlamb.pojo.SMSCode;
import com.mildlamb.service.SMSCodeService;
import com.mildlamb.utils.CodeUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    // 创建缓存空间
    @CreateCache(area = "sms" , name = "smsCache",expire = 360,timeUnit = TimeUnit.SECONDS)
    private Cache<String,String> jetCache;

    @Override
    public String sendCodeToSMS(String tele) {
        // 生成验证码
        String code = CodeUtil.generator(tele);
        jetCache.put(tele,code);
        return code;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = jetCache.get(smsCode.getTele());
        return smsCode.getCode().equals(CacheCode);
    }
}
