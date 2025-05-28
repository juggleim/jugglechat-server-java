package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FriendApplicationItem {
    @JsonProperty("recipient")
    private UserInfo recipient;
    @JsonProperty("sponsor")
    private UserInfo sponsorUser;
    @JsonProperty("target_user")
    private UserInfo targetUser;
    @JsonProperty("is_sponsor")
    private boolean isSponsor;
    @JsonProperty("status")
    private int status;
    @JsonProperty("apply_time")
    private long applyTime;
}
