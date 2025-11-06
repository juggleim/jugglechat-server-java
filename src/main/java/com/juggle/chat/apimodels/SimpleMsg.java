package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SimpleMsg {
    @JsonProperty("msg_id")
    private String msgId;
    @JsonProperty("msg_time")
    private long msgTime;
    @JsonProperty("msg_read_index")
    private long msgReadIndex;
}
