package com.juggle.chat.models;

import lombok.Data;

@Data
public class BlockUser {
    private Long id;
    private String userId;
    private String nickname;
    private String userPortrait;
    private Integer userType;
    private String pinyin;
    private String blockUserId;
    private Long createdTime;
    private String appkey;
}
