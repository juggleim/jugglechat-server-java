package com.juggle.chat.apimodels;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransReq {
    @JsonProperty("items")
    private List<TransItem> items;
    @JsonProperty("source_lang")
    private String sourceLang;
    @JsonProperty("target_lang")
    private String targetLang;
}
