package com.juggle.chat.models;

import lombok.Data;

@Data
public class FriendRel {
    private Long id;
    private String userId;
    private String friendId;
    private String orderTag;
    private String appkey;
}
