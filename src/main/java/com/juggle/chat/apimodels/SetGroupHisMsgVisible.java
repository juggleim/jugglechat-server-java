package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetGroupHisMsgVisible {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("group_his_msg_visible")
    private int groupHisMsgVisible;
}
