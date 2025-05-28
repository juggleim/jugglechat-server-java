package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConfirmFriend {
    @JsonProperty("sponsor_id")
    private String sponsorId;

    @JsonProperty("is_agree")
    private boolean isAgree;
}
