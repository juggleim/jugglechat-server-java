package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RegisterReq {
    @JsonProperty("account")
    private String account;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("code")
    private String code;
    @JsonProperty("password")
    private String password;
}
