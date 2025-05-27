package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SmsLoginReq {
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("code")
    private String code;
}
