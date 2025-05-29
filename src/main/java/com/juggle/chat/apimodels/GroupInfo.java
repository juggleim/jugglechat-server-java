package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupInfo{
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("group_portrait")
    private String groupPortrait;
    @JsonProperty("member_ids")
    private List<String> memberIds;
    @JsonProperty("members")
    private List<GroupMemberInfo> members;
    @JsonProperty("member_count")
    private int memberCount;
    @JsonProperty("owner")
    private GroupMemberInfo owner;
    @JsonProperty("my_role")
    private int myRole;
    @JsonProperty("group_management")
    private GroupManagement grpManagement;
    @JsonProperty("grp_display_name")
    private String grpDisplayName;
    @JsonProperty("member_offset")
    private String memberOffset;
}