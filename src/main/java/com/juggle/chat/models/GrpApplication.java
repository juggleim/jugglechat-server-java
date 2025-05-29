package com.juggle.chat.models;

import lombok.Data;

@Data
public class GrpApplication {
    public static final int GrpApplicationType_Invite = 0;
    public static final int GrpApplicationType_Apply = 1;

    public static final int GrpApplicationStatus_Apply = 0;
    public static final int GrpApplicationStatus_AgreeApply = 1;
    public static final int GrpApplicationStatus_DeclineApply = 2;
    public static final int GrpApplicationStatus_ExpiredApply = 3;

    public static final int GrpApplicationStatus_Invite = 10;
    public static final int GrpApplicationStatus_AgreeInvite = 11;
    public static final int GrpApplicationStatus_DeclineInvite = 12;
    public static final int GrpApplicationStatus_ExpiredInvite = 13;

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
