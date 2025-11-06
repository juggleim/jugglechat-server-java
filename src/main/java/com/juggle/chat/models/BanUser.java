package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BanUser {
    private Long id;
    private String userId;
    private Timestamp createdTime;
    private Long endTime;
    private String scopeKey;
    private String scopeValue;
    private String ext;
    private String appkey;
}
