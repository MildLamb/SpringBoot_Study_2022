package com.mildlamb.controller;

import com.mildlamb.pojo.Role;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestfulController {
    @GetMapping("/role")
    public Role getRole(){
        return new Role("Kindred",1500);
    }

    @RequestMapping(value = "/role",method = RequestMethod.POST)
    public Role getRoleByPost(){
        return new Role("Gnar",9);
    }

    @DeleteMapping("/role/{rid}")
    public String DelRole(@PathVariable("rid") int id){
        return "删除id为：" + id + "角色信息";
    }



}
