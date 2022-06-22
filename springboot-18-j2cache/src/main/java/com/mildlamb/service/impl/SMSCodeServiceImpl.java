package com.mildlamb.service.impl;

import com.mildlamb.pojo.SMSCode;
import com.mildlamb.service.SMSCodeService;
import com.mildlamb.utils.CodeUtil;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    // 创建j2cache缓存对象
    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public String sendCodeToSMS(String tele) {
        // 生成验证码
        String code = CodeUtil.generator(tele);
        // 缓存空间名  键  值
        cacheChannel.set("sms",tele,code);
        return code;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = cacheChannel.get("sms",smsCode.getTele()).asString();
        return smsCode.getCode().equals(CacheCode);
    }
}
