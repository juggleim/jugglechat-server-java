package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GroupMemberExistsResult {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("member_exist_map")
    private Map<String, Boolean> memberExistMap;
}
