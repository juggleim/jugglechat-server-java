package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupMemberInfo {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("member_type")
    private int memberType;
    @JsonProperty("role")
    private int role;
}
