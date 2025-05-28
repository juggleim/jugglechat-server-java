package com.juggle.chat.apimodels;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserInfos {
    @JsonProperty("items")
    private List<UserInfo> items;

    @JsonProperty("offset")
    private String offset;

    public void addUserInf(UserInfo u){
        if(this.items==null){
            this.items = new ArrayList<>();
        }
        this.items.add(u);
    }
}
