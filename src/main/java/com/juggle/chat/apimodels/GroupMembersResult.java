package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GroupMembersResult {
    @JsonProperty("items")
    private List<GroupMemberInfo> items;
    @JsonProperty("offset")
    private String offset;
}
