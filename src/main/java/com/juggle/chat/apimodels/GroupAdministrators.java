package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupAdministrators {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("items")
    private List<GroupMemberInfo> items;
}
