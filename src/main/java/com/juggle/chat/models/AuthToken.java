package com.juggle.chat.models;

import lombok.Data;

@Data
public class AuthToken {
    private String appkey;
    private String userId;
    private String deviceId;
    private long tokenTime;

    public AuthToken(String appkey, String userId, String deviceId, long tokenTime){
        this.appkey = appkey;
        this.userId = userId;
        this.deviceId = deviceId;
        this.tokenTime = tokenTime;
    }
}
