package com.juggle.chat.models;

import lombok.Data;
import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

@Data
public class User {
    @JSONField(serialize = false)
    private Long id;
    @JSONField(name = "user_type")
    private Integer userType;
    @JSONField(name = "user_id")
    private String userId;
    @JSONField(name = "nickname")
    private String nickname;
    @JSONField(name = "user_portrait")
    private String userPortrait;
    @JSONField(name = "pinyin")
    private String pinyin;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "email")
    private String email;
    @JSONField(serialize = false)
    private String loginAccount;
    @JSONField(serialize = false)
    private String loginPass;
    @JSONField(serialize = false)
    private Timestamp createdTime;
    @JSONField(serialize = false)
    private Timestamp updatedTime;
    @JSONField(serialize = false)
    private String appkey;
}
