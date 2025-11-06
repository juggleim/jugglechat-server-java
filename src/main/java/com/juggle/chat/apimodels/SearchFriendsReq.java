package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SearchFriendsReq {
    @JsonProperty("key")
    private String key;
    @JsonProperty("offset")
    private String offset;
    @JsonProperty("limit")
    private int limit;
}
