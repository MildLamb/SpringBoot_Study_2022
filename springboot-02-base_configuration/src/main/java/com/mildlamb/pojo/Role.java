package com.mildlamb.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "role")
public class Role {
    private String name;
    private Integer age;
    private String[] hobbies;
    private Friend[] friends;

    public Role() {
    }

    public Role(String name, Integer age, String[] hobbies,Friend[] friends) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.friends = friends;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Friend[] getFriends() {
        return friends;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
