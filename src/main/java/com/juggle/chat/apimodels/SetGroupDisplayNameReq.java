package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetGroupDisplayNameReq {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("grp_display_name")
    private String grpDisplayName;
}
