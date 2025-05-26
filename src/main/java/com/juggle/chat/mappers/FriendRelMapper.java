package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.FriendRel;

@Mapper
public interface FriendRelMapper {
    int upsert(FriendRel rel);
    int batchUpsert(@Param("rels")List<FriendRel> rels);
    List<FriendRel> queryFriendRels(@Param("appkey")String appkey, @Param("userId")String userId, @Param("startId")long startId, @Param("limit")long limit);
    List<FriendRel> queryFriendRelsWithPage(@Param("appkey")String appkey, @Param("userId")String userId, @Param("orderTag")String orderTag, @Param("page")long page, @Param("size")long size);
    int batchDelete(@Param("appkey")String appkey, @Param("userId")String userId, @Param("friendIds")List<String> friendIds);
    List<FriendRel> queryFriendRelsByFriendIds(@Param("appkey")String appkey, @Param("userId")String userId, @Param("friendIds")List<String> friendIds);
    int updateOrderTag(@Param("appkey")String appkey, @Param("userId")String userId, @Param("friendId")String friendId, @Param("orderTag")String orderTag);
}
