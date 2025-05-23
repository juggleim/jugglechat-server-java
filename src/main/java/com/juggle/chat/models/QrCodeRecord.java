package com.juggle.chat.models;

import lombok.Data;

@Data
public class QrCodeRecord {
    private Long id;
    private String codeId;
    private Integer status;
    private Long createdTime;
    private String userId;
    private String appkey;
}
