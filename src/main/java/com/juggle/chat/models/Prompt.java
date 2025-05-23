package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Prompt {
    private Long id;
    private String userId;
    private String prompts;
    private Timestamp createdTime;
    private String appkey;
}
