package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UpdUserPassReq {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("new_password")
    private String newPassword;
}
