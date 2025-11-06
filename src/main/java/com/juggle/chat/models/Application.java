package com.juggle.chat.models;

import lombok.Data;

@Data
public class Application {
    private Long id;
    private String appId;
    private String appName;
    private String appIcon;
    private String appDesc;
    private String appUrl;
    private Integer appOrder;
    private Long createdTime;
    private Long updatedTime;
    private String appkey;
}
