package com.juggle.chat.apimodels;

import lombok.Data;

@Data
public class LoginReq {
    private String account;
    private String password;
}
