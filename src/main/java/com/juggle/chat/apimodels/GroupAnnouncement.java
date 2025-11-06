package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupAnnouncement {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("content")
    private String content;
}
