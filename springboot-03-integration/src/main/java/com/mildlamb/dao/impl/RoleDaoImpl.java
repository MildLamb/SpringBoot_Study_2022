package com.mildlamb.dao.impl;

import com.mildlamb.dao.RoleDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Override
    public String roleInfo() {
        return "展示珏色信息";
    }
}
