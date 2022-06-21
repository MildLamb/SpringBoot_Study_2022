package com.mildlamb.service.impl;

import com.mildlamb.pojo.SMSCode;
import com.mildlamb.service.SMSCodeService;
import com.mildlamb.utils.CodeUtil;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtil codeUtil;

/*    @Override
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
    }*/

    // 同一个类中调用，没有使用ioc容器中的对象调用方法，注解没有被扫描，使用ioc容器的方法，注解才会被扫描
//    @Cacheable(value = "smsCode",key = "#tele")
//    public String getCacheCode(String tele){
//        return null;
//    }




    // 以下是springboot中使用xmemcached

    @Autowired
    private MemcachedClient memcachedClient;

    @Override
    public String sendCodeToSMS(String tele) {
        String generator = CodeUtil.generator(tele);
        // 参数分别为   键 过期时间(0表示永不过期) 值
        try {
            memcachedClient.set(tele,10,generator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generator;
    }

    @Override
    public boolean checkCode(SMSCode smsCode){
        String CacheCode = null;
        try {
            CacheCode = memcachedClient.get(smsCode.getTele()).toString();
            System.out.println("=================== " + CacheCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsCode.getCode().equals(CacheCode);
    }
}
