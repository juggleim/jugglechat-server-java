package com.juggle.chat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;

@RestController
@RequestMapping("/jim/users")
public class UserController {
    @PostMapping("/update")
    public Result updateUser(){
        return new Result(0, "");
    }

    @PostMapping("/updsettings")
    public Result updateUserSettings(){
        return new Result(0, "");
    }

    @PostMapping("/search")
    public Result searchByPhone(){
        return new Result(0, "");
    }

    @GetMapping("/info")
    public Result qryUserInfo(){
        return new Result(0, "");
    }

    @GetMapping("/qrcode")
    public Result qryUserQrCode(){
        return new Result(0, "");
    }
}
