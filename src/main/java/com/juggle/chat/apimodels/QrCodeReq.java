package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QrCodeReq {
    @JsonProperty("id")
    private String id;
}
