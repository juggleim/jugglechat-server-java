package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AiBotInfo {
    @JsonProperty("bot_id")
    private String botId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("bot_type")
    private int botType;
}
