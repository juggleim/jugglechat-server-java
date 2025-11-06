package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Feedback {
    private String appkey;
    private String userId;
    private String category;
    private byte[] content;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
