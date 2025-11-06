package com.juggle.chat.apimodels;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RecallMsgReq {
    @JsonProperty("from_id")
    private String fromId;
    @JsonProperty("target_id")
    private String targetId;
    @JsonProperty("channel_type")
    private int channelType;
    @JsonProperty("sub_channel")
    private String subChannel;
    @JsonProperty("msg_id")
    private String msgId;
    @JsonProperty("msg_time")
    private long msgTime;
    @JsonProperty("exts")
    private Map<String, String> exts;
}
