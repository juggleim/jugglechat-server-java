package com.juggle.chat.controllers;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.User;
import com.juggle.chat.utils.CommonUtil;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {

        return "Hello, " + name + "!";
    }
}
