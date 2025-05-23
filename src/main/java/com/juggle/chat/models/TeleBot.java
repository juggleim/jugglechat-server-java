package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TeleBot {
    private Long id;
    private String userId;
    private String botName;
    private String botToken;
    private Integer status;
    private Timestamp createdTime;
    private String appkey;
}
