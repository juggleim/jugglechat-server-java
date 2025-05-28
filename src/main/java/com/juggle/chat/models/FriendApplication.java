package com.juggle.chat.models;

import lombok.Data;

@Data
public class FriendApplication {
    public static final int FriendApplicationStatus_Apply = 0;
    public static final int FriendApplicationStatus_Agree = 1;
    public static final int FriendApplicationStatus_Decline = 2;
    public static final int FriendApplicationStatus_Expired = 3;
    
    private Long id;
    private String recipientId;
    private String sponsorId;
    private Long applyTime;
    private Integer status;
    private String appkey;
}
