package com.juggle.chat.models;

import lombok.Data;

@Data
public class BotConf{
    private Long id;
    private String botId;
    private String nickname;
    private String botPortrait;
    private String description;
    private Integer botType;
    private String botConf;
    private Integer status;
    private String appkey;
}