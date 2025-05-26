package com.juggle.chat.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.AiEngine;

@Mapper
public interface AiEngineMapper {
    int create(AiEngine engine);
    AiEngine findById(@Param("appkey")String appkey, @Param("id") long id);
    AiEngine findEnableAiEngine(@Param("appkey")String appkey);
}
