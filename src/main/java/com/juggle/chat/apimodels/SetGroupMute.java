package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetGroupMute {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("is_mute")
    private int isMute;
}
