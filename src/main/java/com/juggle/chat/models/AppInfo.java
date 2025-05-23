package com.juggle.chat.models;

import java.sql.Date;

import lombok.Data;

@Data
public class AppInfo {
    private Long id;
    private String appName;
    private String appkey;
    private String appSecret;
    private String appSecureKey;
    private Integer appStatus;
    private Integer appType;
    private Date createdTime;
    private Date updatedTime;
}
