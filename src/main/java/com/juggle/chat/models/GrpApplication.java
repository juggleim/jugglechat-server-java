package com.juggle.chat.models;

import lombok.Data;

@Data
public class GrpApplication {
    private Long id;
    private String groupId;
    private Integer applyType;
    private String sponsorId;
    private String recipientId;
    private String inviterId;
    private String operatorId;
    private Long applyTime;
    private Integer status;
    private String appkey;
}
