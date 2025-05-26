package com.juggle.chat.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GroupMemberWithUser extends GroupMember{
    private String nickname;
    private String userPortrait;
}
