package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserSettings {
    @JsonProperty("language")
    private String language;

    @JsonProperty("friend_verify_type")
    private int friendVerifyType;

    @JsonProperty("grp_verify_type")
    private int grpVerifyType;

    @JsonProperty("undisturb")
    private String undisturb;
}
