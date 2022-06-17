package com.mildlamb.service.impl;

import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getInfo(Integer id) {
        return roleDao.selectById(id);
    }

    @Override
    public Boolean saveRole(String name, Integer age) {
        return roleDao.insert(new Role(name,age)) > 0;
    }
}
