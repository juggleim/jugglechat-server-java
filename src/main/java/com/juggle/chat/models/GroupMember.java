package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupMember {
    public static final int GrpMemberRole_GrpMember = 0;
    public static final int GrpMemberRole_GrpCreator = 1;
    public static final int GrpMemberRole_GrpAdmin = 2;
    public static final int GrpMemberRole_GrpNotMember = 3;

    private Long id;
    private String groupId;
    private String memberId;
    private Integer memberType;
    private Timestamp createdTime;
    private String appkey;
    private Integer isMute;
    private Integer isAllow;
    private Long muteEndAt;
    private String grpDisplayName;

    private String nickname;
    private String userPortrait;

    private String groupName;
    private String groupPortrait;
}