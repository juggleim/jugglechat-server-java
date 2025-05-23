package com.juggle.chat.models;

import lombok.Data;

@Data
public class FileConf {
    private Long id;
    private String appkey;
    private String channel;
    private String conf;
    private Integer enable;
}
