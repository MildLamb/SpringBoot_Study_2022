package com.mildlamb.service;

import com.mildlamb.pojo.SMSCode;

public interface SMSCodeService {
    String sendCodeToSMS(String tele);
    boolean checkCode(SMSCode smsCode);

}
