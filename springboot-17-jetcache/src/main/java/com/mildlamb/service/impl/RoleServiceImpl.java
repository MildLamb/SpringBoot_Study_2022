package com.mildlamb.service.impl;

import com.alicp.jetcache.anno.*;
import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    // 启用方法缓存
    @Cached(name = "roles_",key = "#rid",expire = 360,timeUnit = TimeUnit.SECONDS,cacheType = CacheType.REMOTE)
    // 刷新缓存 重新查询重新缓存  时间要考虑清楚
    @CacheRefresh(refresh = 10,timeUnit = TimeUnit.SECONDS)
    public Role getRoleById(Integer rid) {
        return roleDao.selectById(rid);
    }

    @Override
    public boolean saveRole(Role role) {
        return roleDao.insert(role) > 0;
    }

    @Override
    // 执行更新操作时，更新缓存
    // 缓存哪个缓存空间   键   值
    @CacheUpdate(name = "roles_",key = "#role.id",value = "#role")
    public boolean updateRole(Role role) {
        return roleDao.updateById(role) > 0;
    }

    @Override
    // 执行删除操作时，删除缓存
    @CacheInvalidate(name = "roles_",key = "#id")
    public boolean deleteRole(Integer id) {
        return roleDao.deleteById(id) > 0;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.selectList(null);
    }
}
