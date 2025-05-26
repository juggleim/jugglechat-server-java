package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.GroupExt;

@Mapper
public interface GroupExtMapper {
    GroupExt find(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("itemKey")String itemKey);
    List<GroupExt> qryExtFields(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int upsert(GroupExt groupExt);
}
