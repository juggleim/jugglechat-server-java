package com.juggle.chat.models;

import lombok.Data;

@Data
public class ConverConf {
    private Long id;
    private String converId;
    private Integer converType;
    private String subChannel;
    private String itemKey;
    private String itemValue;
    private Integer itemType;
    private String appkey;
}
