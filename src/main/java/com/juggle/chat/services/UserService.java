package com.juggle.chat.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.User;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User QryUserInfo(String userId){
        User user = userMapper.findByUserId("appkey",userId);
        System.out.println("xxxxxx::"+user.getAppkey());
        return user;
    }
}
