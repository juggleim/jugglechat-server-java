package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetGroupVerifyType {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("verify_type")
    private int verifyType;
}
