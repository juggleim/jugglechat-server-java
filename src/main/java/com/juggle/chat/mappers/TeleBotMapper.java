package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.TeleBot;

@Mapper
public interface TeleBotMapper {
    int create(TeleBot bot);
    TeleBot findById(@Param("id") Long id, @Param("appkey") String appkey, @Param("userId") String userId);
    int batchDelete(@Param("appkey") String appkey, @Param("userId") String userId, @Param("botIds") List<Long> botIds);
    List<TeleBot> qryTeleBots(@Param("appkey") String appkey, @Param("userId") String userId, @Param("startId") Long startId, @Param("limit") Long limit);
}
