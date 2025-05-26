package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.GroupMember;

@Mapper
public interface GroupMemberMapper {
    int create(GroupMember grpMember);
    int batchCreate(@Param("grpMembers")List<GroupMember> grpMembers);
    GroupMember find(@Param("appkey")String appkey, @Param("groupId")String groupId,@Param("memberId")String memberId);
    List<GroupMember> findByMemberIds(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("memberIds")List<String> memberIds);
    List<GroupMember> queryMembers(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("startId")long startId, @Param("limit")long limit);
    List<GroupMember> queryGroupsByMemberId(@Param("appkey")String appkey, @Param("memberId")String memberId, @Param("startId")long startId, @Param("limit")long limit);
    int batchDelete(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("memberIds")List<String> memberIds);
    int deleteByGroupId(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int updateMute(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("isMute")int isMute, @Param("memberIds")List<String> memberIds, @Param("muteEndAt")long muteEndAt);
    int updateAllow(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("isAllow")int isAllow, @Param("memberIds")List<String> memberIds);
    int countByGroup(@Param("appkey")String appkey, @Param("groupId")String groupId);
    int updateGrpDisplayName(@Param("appkey")String appkey, @Param("groupId")String groupId, @Param("memberId")String memberId, @Param("displayName")String displayName);
}
