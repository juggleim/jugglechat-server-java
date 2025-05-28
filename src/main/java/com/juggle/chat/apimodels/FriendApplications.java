package com.juggle.chat.apimodels;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FriendApplications {
    @JsonProperty("items")
    private List<FriendApplicationItem> items;

    public void addFriendApplication(FriendApplicationItem item){
        if(this.items==null){
            this.items = new ArrayList<>();
        }
        this.items.add(item);
    }
}
