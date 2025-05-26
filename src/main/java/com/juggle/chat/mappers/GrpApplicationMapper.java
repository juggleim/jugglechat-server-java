package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.GrpApplication;

@Mapper
public interface GrpApplicationMapper {
    int inviteUpsert(GrpApplication grpApplication);
    int applyUpsert(GrpApplication grpApplication);
    List<GrpApplication> queryMyGrpApplications(@Param("appkey")String appkey, @Param("sponsorId")String sponsorId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    List<GrpApplication> queryMyPendingGrpInvitations(@Param("appkey")String appkey, @Param("recipientId")String recipientId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    List<GrpApplication> queryGrpInvitations(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
    List<GrpApplication> queryGrpPendingApplications(@Param("appkey")String appkey,@Param("groupId")String groupId, @Param("startTime")long startTime, @Param("count")long count, @Param("isPositive")Boolean isPositive);
}
