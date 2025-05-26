package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.FriendApplication;

@Mapper
public interface FriendApplicationMapper {
    int upsert(FriendApplication app);
    List<FriendApplication> queryPendingApplications(@Param("appkey")String appkey, @Param("recipientId")String recipientId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    List<FriendApplication> queryMyApplications(@Param("appkey")String appkey, @Param("sponsorId")String sponsorId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    List<FriendApplication> queryApplications(@Param("appkey")String appkey, @Param("userId")String userId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    int updateStatus(@Param("appkey")String appkey, @Param("sponsorId")String sponsorId, @Param("recipientId")String recipientId, @Param("status")int status);
}
