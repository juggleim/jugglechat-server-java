package com.juggle.chat.controllers;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.FileConfMapper;
import com.juggle.chat.mappers.FriendApplicationMapper;
import com.juggle.chat.mappers.UserMapper;
import com.juggle.chat.models.AuthToken;
import com.juggle.chat.models.FriendApplication;
import com.juggle.chat.models.User;
import com.juggle.chat.services.AppInfoCache;
import com.juggle.chat.services.AuthTokenService;
import com.juggle.chat.services.UserService;
import com.juggle.chat.services.Appbus;
import com.juggle.chat.utils.AESUtil;
import com.juggle.chat.utils.CommonUtil;

@RestController
@RequestMapping("/jim")
public class HelloController {
    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        AuthToken token = new AuthToken("appkey","userid1","",System.currentTimeMillis());
        try {
            String tokenStr = AuthTokenService.toTokenString(token, "abcdefghijklmnop");
            System.out.println(tokenStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Hello, " + name + "!";
    }

    @GetMapping("validate")
    public String validate(){
        RequestContext ctx = RequestContext.get();
        System.out.println(ctx.getAppkey()+" "+ctx.getCurrentUserId());
        return "ok";
    }
}
