package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SearchReq {
    @JsonProperty("keyword")
    private String keyword;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("limit")
    private int limit;
    @JsonProperty("offset")
    private String offset;
}
