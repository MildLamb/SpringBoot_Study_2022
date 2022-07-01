package cn.engulf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("ipProperties")
@ConfigurationProperties(prefix = "tools.ip")
public class IpCountProperties {
    /**
     * 日志的显式周期
     */
    private Integer cycle = 5;

    /**
     * 是否周期性重置数据
     */
    private Boolean countFlush = false;

    /**
     * 日志输出模式
     * detail：详细模式    simple：简单模式
     */
    private String model = LogModel.DETAIL.value;

    public enum LogModel{
        DETAIL("detail"),
        SIMPLE("simple");
        private String value;

        LogModel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Boolean getCountFlush() {
        return countFlush;
    }

    public void setCountFlush(Boolean countFlush) {
        this.countFlush = countFlush;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
