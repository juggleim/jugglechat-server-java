package com.juggle.chat.models;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class UserExt {
    private Long id;
    private String userId;
    private String itemKey;
    private String itemValue;
    private Integer itemType;
    private Timestamp updatedTime;
    private String appkey;
}
