package com.mildlamb.controller;

import com.mildlamb.pojo.Role;
import com.mildlamb.service.MsgService;
import com.mildlamb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/{tel}")
    public String get(@PathVariable("tel") String tel){
        return msgService.get(tel);
    }

    @PostMapping("/check")
    public boolean check(String tel,String code){
        return msgService.checkTel(tel,code);
    }


}
