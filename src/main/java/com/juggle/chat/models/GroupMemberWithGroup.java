package com.juggle.chat.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GroupMemberWithGroup {
    private String groupName;
    private String groupPortrait;
}
