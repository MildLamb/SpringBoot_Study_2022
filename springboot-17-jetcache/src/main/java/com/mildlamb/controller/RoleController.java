package com.mildlamb.controller;

import com.mildlamb.pojo.Role;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{rid}")
    public Role getRoleById(@PathVariable("rid") Integer id){
        return roleService.getRoleById(id);
    }

    @GetMapping("/all")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("/save/{rname}/{rage}")
    public boolean saveRole(@PathVariable("rname") String name,@PathVariable("rage") Integer age){
        Role role = new Role(null,name,age);
        return roleService.saveRole(role);
    }

    @GetMapping("/del/{rid}")
    public boolean delRole(@PathVariable("rid") Integer id){
        return roleService.deleteRole(id);
    }
}
