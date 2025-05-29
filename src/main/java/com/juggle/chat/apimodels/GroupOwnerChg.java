package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupOwnerChg {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("owner_id")
    private String ownerId;
}
