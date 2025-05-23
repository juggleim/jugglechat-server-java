package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SmsRecord {
    private Long id;
    private String phone;
    private String code;
    private Timestamp createdTime;
    private String appkey;
}
