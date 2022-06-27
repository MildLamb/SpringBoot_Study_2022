package com.mildlamb.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoConfig implements InfoContributor {

    @Value("${my-appInfo.artifact}")
    private String artifact;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("runTime",System.currentTimeMillis());
        Map infoMap = new HashMap();
        infoMap.put("author","MildLamb");
        infoMap.put("appName",artifact);
        infoMap.put("特别鸣谢","温柔小羊");
        builder.withDetails(infoMap);
    }
}
