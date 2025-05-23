package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Group {
    private Long id;
    private String groupId;
    private String groupName;
    private String groupPortrait;
    private String creatorId;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String appkey;
    private Integer isMute;
}
