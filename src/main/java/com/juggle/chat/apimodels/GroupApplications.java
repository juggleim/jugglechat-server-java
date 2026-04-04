package com.juggle.chat.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GroupApplications {
    @JsonProperty("items")
    private List<GroupApplicationItem> items;
}
