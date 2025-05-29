package com.juggle.chat.apimodels;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupInviteResp {
    public static final int GrpInviteReason_InviteSucc = 0;
    public static final int GrpInviteReason_InviteSendOut = 1;
    public static final int GrpInviteReason_InviteDecline = 2;
    public static final int GrpInviteReason_InviteRepeated = 3;

    @JsonProperty("results")
    private Map<String,Integer> results;
}
