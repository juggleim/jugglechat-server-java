package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GroupConfirm {
    @JsonProperty("application_id")
    private String applicationId;
    @JsonProperty("is_agree")
    private boolean agree;
}
