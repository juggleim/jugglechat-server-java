package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.BotConf;

@Mapper
public interface BotConfMapper {
    int upsert(BotConf botConf);
    BotConf findById(@Param("appkey")String appkey, @Param("botId")String botId);
    List<BotConf> qryBotConfs(@Param("appkey")String appkey, @Param("startId")long startId, @Param("limit")long limit);
    List<BotConf> qryBotConfsWithStatus(@Param("appkey")String appkey, @Param("status")int status, @Param("startId")long startId, @Param("limit")long limit);
    int updateStatus(@Param("appkey")String appkey, @Param("botId")String botId, @Param("status")int status);
}
