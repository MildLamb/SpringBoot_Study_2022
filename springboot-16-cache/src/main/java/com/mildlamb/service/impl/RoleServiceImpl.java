package com.mildlamb.service.impl;

import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    // 模拟缓存
    private HashMap<Integer,Role> cache = new HashMap();

    // 自己模拟缓存
    /*@Override
    public Role getRoleById(Integer id) {
        // 如果当前缓存中没有本次要查询的数据，则进行查询，否则直接从缓存中获取
        Role role = cache.get(id);
        if (role == null) {
            role = roleDao.selectById(id);
            cache.put(id,role);
        }
        return role;
    }*/

    @Override
    // value指定缓存名称  key指定缓存中的键  #paramName可以取到对应参数
    @Cacheable(value = "cacheSpace",key = "#rid")
    public Role getRoleById(Integer rid) {
        return roleDao.selectById(rid);
    }

    @Override
    public boolean saveRole(Role role) {
        return roleDao.insert(role) > 0;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleDao.update(role,null) > 0;
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleDao.deleteById(id) > 0;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.selectList(null);
    }
}
