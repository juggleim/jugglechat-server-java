package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupAdminIds {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("admin_ids")
    private List<String> adminids;
}
