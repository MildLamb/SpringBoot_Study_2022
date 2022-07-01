package cn.engulf.properties;

import org.springframework.stereotype.Component;

@Component("kindred")
public class TestProperties {
    private Integer cycle = 6;

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }
}
