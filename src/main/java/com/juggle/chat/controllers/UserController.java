package com.juggle.chat.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.UserInfo;
import com.juggle.chat.apimodels.UserSettings;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.UserService;

@RestController
@RequestMapping("/jim/users")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/update")
    public Result updateUser(@RequestBody UserInfo user)throws JimException{
        userService.updateUser(user);
        return new Result(0, "");
    }

    @PostMapping("/updsettings")
    public Result updateUserSettings(@RequestBody UserSettings settings)throws JimException{
        this.userService.updateUserSettings(settings);
        return new Result(0, "");
    }

    @PostMapping("/search")
    public Result searchByPhone(@RequestBody UserInfo user)throws JimException{
        
        return new Result(0, "");
    }

    @GetMapping("/info")
    public Result qryUserInfo(@RequestParam("user_id") String userId){
        UserInfo user = this.userService.qryUserInfo(userId);
        return Result.success(user);
    }

    @GetMapping("/qrcode")
    public Result qryUserQrCode(){
        return new Result(0, "");
    }
}
