package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QrCode {
    @JsonProperty("qr_code")
    private String qrCode;
}
