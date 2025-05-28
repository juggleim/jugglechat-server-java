package com.juggle.chat.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.LoginReq;
import com.juggle.chat.apimodels.LoginUserResp;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.SmsLoginReq;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.LoginService;

@RestController
@RequestMapping("/jim")
public class LoginController {
    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginReq req)throws JimException{
        throw new JimException(1, "");
        // return new Result(0, "succ");
    }

    @GetMapping("/login/qrcode")
    public Result generateQrCode(){
        return new Result(0,"succ");
    }

    @PostMapping("/login/qrcode/check")
    public Result checkQrCode(){
        return new Result(0,"succ");
    }

    @PostMapping("/sms/send")
    public Result smsSend(){
        return new Result(0, "succ");
    }

    @PostMapping(value = {"/sms/login","/sms_login"})
    public Result smsLogin(@RequestBody SmsLoginReq req)throws JimException{
        if(req==null||req.getPhone().isEmpty()||req.getCode().isEmpty()){
            throw new JimException(JimErrorCode.ErrorCode_APP_REQ_BODY_ILLEGAL, "");
        }
        LoginUserResp resp = loginService.smsLogin(req.getPhone(), req.getCode());
        return Result.success(resp);
    }

    @PostMapping("/email/send")
    public Result emailSend(){
        return new Result(0, "succ");
    }

    @PostMapping("/email/login")
    public Result emailLogin(){
        return new Result(0, "succ");
    }

    @PostMapping("/login/qrcode/confirm")
    public Result confirmQrCode(){
        return new Result(0, "succ");
    }
}
