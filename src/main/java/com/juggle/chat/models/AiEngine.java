package com.juggle.chat.models;

import lombok.Data;

@Data
public class AiEngine {
    private Long id;
    private Integer engineType;
    private String engineConf;
    private Integer status;
    private String appkey;
}
