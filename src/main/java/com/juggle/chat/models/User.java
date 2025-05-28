package com.juggle.chat.models;

import lombok.Data;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

@Data
public class User {
    @JSONField(serialize = false)
    private Long id;
    private Integer userType;
    private String userId;
    private String nickname;
    private String userPortrait;
    private String pinyin;
    private String phone;
    private String email;
    private String loginAccount;
    private String loginPass;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String appkey;
}
