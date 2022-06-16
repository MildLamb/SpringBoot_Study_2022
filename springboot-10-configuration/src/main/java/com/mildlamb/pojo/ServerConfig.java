package com.mildlamb.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

//@Component
@Data
@ConfigurationProperties(prefix = "server-config")

// 开启对当前bean的属性校验
@Validated
public class ServerConfig {
    private String ipAddress;
    // 制定校验规则
    @Max(value = 8888,message = "最大值不能超过8888")
    @Min(value = 0,message = "最小值不能低于0")
    private int port;
    private long timeout;

    // 时间类型 Duration
    // 指定时间单位
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration serverTimeOut;

    // 容量类型
    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;



}
