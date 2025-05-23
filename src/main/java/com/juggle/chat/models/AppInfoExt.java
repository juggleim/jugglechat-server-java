package com.juggle.chat.models;


import java.sql.Date;

import lombok.Data;

@Data
public class AppInfoExt {
    private Long id;
    private String appkey;
    private String appItemKey;
    private String appItemValue;
    private Date updatedTime;
}
