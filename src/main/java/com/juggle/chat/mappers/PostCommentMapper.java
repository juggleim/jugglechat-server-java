package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.PostComment;

@Mapper
public interface PostCommentMapper {
    int create(PostComment postComment);
    List<PostComment> findByIds(@Param("appkey")String appkey, @Param("commentIds")List<String> commentIds);
    List<PostComment> qryPostComments(@Param("appkey")String appkey, 
                                         @Param("postId")String postId, 
                                         @Param("startTime")long startTime, 
                                         @Param("limit")long limit, 
                                         @Param("isPositive")Boolean isPositive);
}
