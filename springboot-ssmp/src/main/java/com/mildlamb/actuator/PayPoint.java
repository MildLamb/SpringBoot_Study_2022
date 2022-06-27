package com.mildlamb.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id="pay",enableByDefault = true)
public class PayPoint {
    @ReadOperation
    public Object getPay(){
        System.out.println("======================");
        System.out.println("========= pay ========");
        System.out.println("======================");
        Map payMap = new HashMap();
        payMap.put("level 5",300);
        return payMap;
    }
}
