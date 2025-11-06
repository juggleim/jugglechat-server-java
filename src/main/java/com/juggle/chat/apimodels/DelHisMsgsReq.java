package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DelHisMsgsReq {
    @JsonProperty("from_id")
    private String fromId;
    @JsonProperty("target_id")
    private String targetId;
    @JsonProperty("channel_type")
    private int channelType;
    @JsonProperty("sub_channel")
    private String subChannel;
    @JsonProperty("msgs")
    private List<SimpleMsg> msgs;
}
