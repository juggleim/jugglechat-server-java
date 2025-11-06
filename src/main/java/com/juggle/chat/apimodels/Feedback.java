package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Feedback {
    @JsonProperty("category")
    private String category;
    @JsonProperty("text")
    private String text;
    @JsonProperty("images")
    private List<String> images;
    @JsonProperty("videos")
    private List<String> videos;
}
