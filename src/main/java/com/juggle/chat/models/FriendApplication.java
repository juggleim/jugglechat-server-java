package com.juggle.chat.models;

import lombok.Data;

@Data
public class FriendApplication {
    private Long id;
    private String recipientId;
    private String sponsorId;
    private Long applyTime;
    private Integer status;
    private String appkey;
}
