package com.mildlamb.service.impl;

import com.mildlamb.service.MsgService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MsgServiceImpl implements MsgService {

    private HashMap<String,String> cache = new HashMap<>();

    @Override
    public String get(String tale) {
        String code = tale.substring(tale.length() - 6);
        cache.put(tale,code);
        return code;
    }

    @Override
    public boolean checkTel(String tale, String code) {
        String queryCode = cache.get(tale);
        return code.equals(queryCode);
    }
}
