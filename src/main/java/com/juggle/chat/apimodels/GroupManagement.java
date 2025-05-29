package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupManagement {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("group_mute")
    private int groupMute;
    @JsonProperty("max_admin_count")
    private int maxAdminCount;
    @JsonProperty("admin_count")
    private int adminCount;
    @JsonProperty("group_verify_type")
    private int groupVerifyType;
    @JsonProperty("group_his_msg_visible")
    private int groupHisMsgVisible;
}
