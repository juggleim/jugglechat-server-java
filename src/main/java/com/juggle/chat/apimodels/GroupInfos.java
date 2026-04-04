package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GroupInfos {
    @JsonProperty("items")
    private List<GroupInfo> items;
    @JsonProperty("offset")
    private String offset;
}
