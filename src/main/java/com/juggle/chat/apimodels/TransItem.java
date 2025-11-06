package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransItem {
    @JsonProperty("key")
    private String key;
    @JsonProperty("content")
    private String content;
}
