package com.juggle.chat.controllers;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.juggle.chat.mappers.FileConfMapper;
import com.juggle.chat.mappers.FriendApplicationMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.FriendApplication;
import com.juggle.chat.models.User;
import com.juggle.chat.utils.CommonUtil;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private FriendApplicationMapper friendAppMapper;


    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        List<FriendApplication> list = friendAppMapper.queryApplications("appkey","userid1",1748255422002L,10,false);
        System.out.println(CommonUtil.toJson(list));
        for (FriendApplication app : list) {
            System.out.println( app.getRecipientId() + " " +app.getSponsorId());
        }
        return "Hello, " + name + "!";
    }
}
