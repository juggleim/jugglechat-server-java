package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.AppInfoExt;

@Mapper
public interface AppInfoExtMapper {
    List<AppInfoExt> findListByAppkey(@Param("appkey") String appkey);
    AppInfoExt find(@Param("appkey")String appkey, @Param("itemKey")String itemKey);
    List<AppInfoExt> findByItemKeys(@Param("appkey")String appkey, @Param("itemKeys")List<String> itemKeys);
    int upsert(@Param("appkey")String appkey, @Param("itemKey")String itemKey, @Param("itemValue")String itemValue);
}
