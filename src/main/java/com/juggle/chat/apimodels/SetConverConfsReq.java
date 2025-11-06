package com.juggle.chat.apimodels;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SetConverConfsReq {
    @JsonProperty("target_id")
    private String targetId;
    @JsonProperty("sub_channel")
    private String subChannel;
    @JsonProperty("conver_type")
    private int converType;
    @JsonProperty("confs")
    private Map<String, Object> confs;
}
