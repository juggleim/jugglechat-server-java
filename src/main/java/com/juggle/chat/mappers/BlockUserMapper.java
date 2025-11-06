package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.BlockUser;

@Mapper
public interface BlockUserMapper {
    int create(BlockUser blockUser);
    int delBlockUser(@Param("appkey") String appkey, @Param("userId") String userId, @Param("blockUserId") String blockUserId);
    int batchDelBlockUsers(@Param("appkey") String appkey, @Param("userId") String userId, @Param("blockUserIds") List<String> blockUserIds);
    BlockUser find(@Param("appkey") String appkey, @Param("userId") String userId, @Param("blockUserId") String blockUserId);
    List<BlockUser> findBlockUserByIds(@Param("appkey") String appkey, @Param("userId") String userId, @Param("blockUserIds") List<String> blockUserIds);
    List<BlockUser> qryBlockUsers(@Param("appkey") String appkey, @Param("userId") String userId, @Param("limit") long limit, @Param("startId") long startId);
}
