package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Friend {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("friend_id")
    private String friendId;
}
