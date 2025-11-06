package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetUserAccountReq {
    @JsonProperty("account")
    private String account;
    @JsonProperty("password")
    private String password;
}
