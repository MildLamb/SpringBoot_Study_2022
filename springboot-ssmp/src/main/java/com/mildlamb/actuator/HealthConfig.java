package com.mildlamb.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HealthConfig extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map infoMap = new HashMap();
        infoMap.put("author","MildLamb");
        infoMap.put("特别鸣谢","温柔小羊");
        builder.withDetails(infoMap);
        builder.status(Status.UP);
    }
}
