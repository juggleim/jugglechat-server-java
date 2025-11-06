package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Application {
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("app_name")
    private String appName;
    @JsonProperty("app_icon")
    private String appIcon;
    @JsonProperty("app_desc")
    private String appDesc;
    @JsonProperty("app_url")
    private String appUrl;
    @JsonProperty("app_order")
    private int appOrder;
    @JsonProperty("created_time")
    private long createdTime;
    @JsonProperty("updated_time")
    private long updatedTime;
}
