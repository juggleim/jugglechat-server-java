package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupAdmin {
    private Long id;
    private String groupId;
    private String adminId;
    private Timestamp createdTime;
    private String appkey;
}
