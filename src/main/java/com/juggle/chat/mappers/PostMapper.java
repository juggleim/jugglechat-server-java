package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.Post;

@Mapper
public interface PostMapper {
    int create(Post post);
    Post findById(@Param("appkey")String appkey, @Param("postId")String postId);
    List<Post> findByIds(@Param("appkey")String appkey,@Param("postIds")List<String> postIds);
    List<Post> qryPosts(@Param("appkey")String appkey, @Param("startTime")Long startTime, @Param("limit")long limit,@Param("isPositive")Boolean isPositive);
}
