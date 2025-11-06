package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SearchGroupMembersReq {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("key")
    private String key;
    @JsonProperty("offset")
    private String offset;
    @JsonProperty("limit")
    private int limit;
}
