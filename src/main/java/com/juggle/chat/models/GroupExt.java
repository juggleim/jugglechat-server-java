package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class GroupExt {
    private Long id;
    private String groupId;
    private String itemKey;
    private String itemValue;
    private Integer itemType;
    private Timestamp updatedTime;
    private String appkey;
}
