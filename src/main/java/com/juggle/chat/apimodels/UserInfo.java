package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserInfo {
    @JsonProperty("user_id")
    private String userId;
    
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("pinyin")
    private String pinyin;

    @JsonProperty("user_type")
    private int userType;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("status")
    private int status;

    @JsonProperty("is_friend")
    private boolean isFriend;

    @JsonProperty("settings")
    private UserSettings settings;
}


