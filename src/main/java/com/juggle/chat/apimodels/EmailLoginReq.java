package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmailLoginReq {
    @JsonProperty("email")
    private String email;
    @JsonProperty("code")
    private String code;
}
