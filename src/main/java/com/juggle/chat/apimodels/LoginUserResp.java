package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginUserResp {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("authorization")
    private String authorization;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("im_token")
    private String imToken;
}
