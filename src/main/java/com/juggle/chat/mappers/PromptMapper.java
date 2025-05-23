package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.Prompt;

@Mapper
public interface PromptMapper {
    int create(Prompt prompt);
    int updatePrompt(@Param("appkey")String appkey, @Param("userId")String userId,@Param("id")Long id, @Param("prompts")String prompts);
    int deletePrompt(@Param("appkey")String appkey, @Param("userId")String userId,@Param("id")Long id);
    int batchDeletePrompts(@Param("appkey")String appkey, @Param("userId")String userId,@Param("ids")List<Long> ids);
    Prompt findPrompt(@Param("appkey")String appkey, @Param("userId")String userId, @Param("id")Long id);
    List<Prompt> qryPrompts(@Param("appkey")String appkey, @Param("userId")String userId,@Param("limit")Long limit, @Param("startId")Long startId);
}
