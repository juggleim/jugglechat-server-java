package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupMember {
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
}