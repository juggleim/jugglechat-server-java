package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Applications {
    @JsonProperty("items")
    private List<Application> items;
    @JsonProperty("offset")
    private String offset;
    @JsonProperty("page")
    private int page;
    @JsonProperty("size")
    private int size;
}
