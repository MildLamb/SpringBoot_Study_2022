package com.mildlamb.utils;

import com.mildlamb.pojo.SMSCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtil {
    public static String generator(String tele){
        int hash = tele.hashCode();
        int intencryption = 20220621;
        long result = hash ^ intencryption;
        long time = System.currentTimeMillis();
        String code = Math.abs(result ^ time)+"";
        return code.substring(code.length()-6);
    }


    // 获取缓存中的值，如果缓存中存在，则直接获取，不存在则返回return的值
    @Cacheable(value = "smsCheckCode",key = "#tele")
    public String getCacheCode(String tele){
        return null;
    }

//    public static void main(String[] args) {
//        String generator = generator("15873562351");
//        System.out.println(generator);
//    }
}
