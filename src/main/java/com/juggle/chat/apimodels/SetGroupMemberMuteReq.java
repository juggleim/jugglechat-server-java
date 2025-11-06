package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetGroupMemberMuteReq {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("member_ids")
    private List<String> memberIds;
    @JsonProperty("is_mute")
    private int mute;
}
