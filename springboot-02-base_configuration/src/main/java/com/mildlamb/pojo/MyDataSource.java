package com.mildlamb.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 1. 定义数据模型准备封装yml文件中的数据
 * 2. 将数据模型交给Spring管理
 * 3. 指定加载的数据是哪个 @ConfigurationProperties(prefix = "属性前缀名称")
 */

@Component
@ConfigurationProperties(prefix = "datasource")
public class MyDataSource {
    private String driver;
    private String url;
    private String username;
    private String password;

    public MyDataSource() {
    }

    public MyDataSource(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
