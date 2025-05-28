package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AiBotInfos {
    @JsonProperty("items")
    private List<AiBotInfo> items;

    @JsonProperty("offset")
    private String offset;
}
