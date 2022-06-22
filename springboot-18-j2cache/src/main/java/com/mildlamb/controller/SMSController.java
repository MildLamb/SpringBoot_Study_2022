package com.mildlamb.controller;

import com.mildlamb.pojo.SMSCode;
import com.mildlamb.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private SMSCodeService smsCodeService;

    @GetMapping("/getCode/{tele}")
    public String getCode(@PathVariable("tele") String tele){
        return smsCodeService.sendCodeToSMS(tele);
    }

    @PostMapping("/check")
    public boolean checkCode(SMSCode smsCode){
        return smsCodeService.checkCode(smsCode);
    }
}
