package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.ConverConf;

@Mapper
public interface ConverConfMapper {
    int upsert(ConverConf converConf);
    int batchUpsert(@Param("items") List<ConverConf> items);
    List<ConverConf> qryConverConfs(
        @Param("appkey") String appkey,
        @Param("converId") String converId,
        @Param("subChannel") String subChannel,
        @Param("converType") int converType);
}
