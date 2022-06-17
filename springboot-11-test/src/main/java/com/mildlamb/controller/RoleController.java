package com.mildlamb.controller;

import com.mildlamb.pojo.Role;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/info")
    public String getInfo(){
        System.out.println("Info of Kindred");
        return "这是珏宝的一些信息";
    }

    @GetMapping("/myrole/{rid}")
    public Role getRole(@PathVariable("rid") Integer id){
        return roleService.getInfo(id);
    }

    @PostMapping("/save/{rname}/{rage}")
    public Boolean saveRole(@PathVariable("rname") String name,@PathVariable("rage") Integer age){
        return roleService.saveRole(name,age);
    }
}
