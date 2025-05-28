package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FriendIds {
    @JsonProperty("friend_ids")
    private List<String> friendIds;
}
