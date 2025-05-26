package com.juggle.chat.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.Group;

@Mapper
public interface GroupMapper {
    int create(Group group);
    Boolean isExist(@Param("appkey")String appkey, @Param("groupId")String groupId);
    Group findById(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int delete(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int updateGroupMuteStatus(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("isMute")int isMute);
    int updateGrpName(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("groupName")String groupName, @Param("groupPortrait")String groupPortrait);
    int updateCreatorId(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("creatorId")String creatorId);
}
