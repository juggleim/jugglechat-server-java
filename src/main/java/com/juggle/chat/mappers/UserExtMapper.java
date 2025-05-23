package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.juggle.chat.models.UserExt;

@Mapper
public interface UserExtMapper {
    int upsert(UserExt ext);
    int batchUpsert(@Param("exts")List<UserExt> exts);
    int batchDelete(@Param("appkey") String appkey,@Param("itemKey")String itemKey, @Param("userIds")List<String> userIds);

    List<UserExt> qryExtFields(@Param("appkey")String appkey, @Param("userId")String userId);
    List<UserExt> qryExtFieldsByItemKeys(@Param("appkey")String appkey,@Param("userId") String userId, @Param("itemKeys")List<String> itemKeys);
    List<UserExt> qryExtsBaseItemKey(@Param("appkey")String appkey, @Param("itemKey")String itemKey, @Param("startId")Long startId, @Param("limit")Long limit);
}
