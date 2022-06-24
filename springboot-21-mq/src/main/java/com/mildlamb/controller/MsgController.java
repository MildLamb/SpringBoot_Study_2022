package com.mildlamb.controller;

import com.mildlamb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String doMessage(){
        return messageService.doMessage();
    }
}
