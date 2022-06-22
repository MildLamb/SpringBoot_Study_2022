package com.mildlamb.service;

import com.mildlamb.pojo.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Integer id);
    boolean saveRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(Integer id);
    List<Role> getRoles();

}
