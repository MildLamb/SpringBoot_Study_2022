package com.mildlamb.service;

import com.mildlamb.pojo.Role;

public interface RoleService {
    Role getInfo(Integer id);
    Boolean saveRole(String name, Integer age);
}
