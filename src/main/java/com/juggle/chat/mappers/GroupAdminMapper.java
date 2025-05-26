package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.GroupAdmin;

@Mapper
public interface GroupAdminMapper {
    int upsert(GroupAdmin grpAdmin);
    List<GroupAdmin> qryAdmins(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int batchDel(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("adminIds")List<String> adminIds);
}
