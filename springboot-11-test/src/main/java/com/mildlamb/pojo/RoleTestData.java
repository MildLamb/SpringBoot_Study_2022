package com.mildlamb.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "testdata.role")
public class RoleTestData {
    private int id;
    private int id2;
    private int type;
    private String name;
    private String uuid;
    private long roleTime;
}
