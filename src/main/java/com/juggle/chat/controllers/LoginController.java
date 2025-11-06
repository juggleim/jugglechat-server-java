package com.juggle.chat.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juggle.chat.apimodels.LoginReq;
import com.juggle.chat.apimodels.LoginUserResp;
import com.juggle.chat.apimodels.QrCodeReq;
import com.juggle.chat.apimodels.Result;
import com.juggle.chat.apimodels.RegisterReq;
import com.juggle.chat.apimodels.SmsLoginReq;
import com.juggle.chat.apimodels.EmailLoginReq;
import com.juggle.chat.exceptions.JimErrorCode;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.services.LoginService;

@RestController
@RequestMapping("/jim")
public class LoginController {
    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginReq req) throws JimException {
        return new Result(0, "");
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterReq req) {
        return new Result(0, "");
    }

    @GetMapping("/login/qrcode")
    public Result generateQrCode() {
        return new Result(0, "");
    }

    @PostMapping("/login/qrcode/check")
    public Result checkQrCode(@RequestBody QrCodeReq req) {
        return new Result(0, "");
    }

    @PostMapping("/sms/send")
    public Result smsSend(@RequestBody SmsLoginReq req) {
        return new Result(0, "");
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
    public Result emailSend(@RequestBody EmailLoginReq req) {
        return new Result(0, "");
    }

    @PostMapping("/email/login")
    public Result emailLogin(@RequestBody EmailLoginReq req) {
        return new Result(0, "");
    }

    @PostMapping("/login/qrcode/confirm")
    public Result confirmQrCode(@RequestBody QrCodeReq req) {
        return new Result(0, "");
    }
}
