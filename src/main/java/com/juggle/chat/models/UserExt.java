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

    public UserExt(){}

    public UserExt(String appkey, String userId, String itemKey, String itemValue, Integer itemType){
        this.appkey = appkey;
        this.userId = userId;
        this.itemKey = itemKey;
        this.itemValue = itemValue;
        this.itemType = itemType;
    }
}
