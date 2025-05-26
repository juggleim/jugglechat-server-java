package com.juggle.chat.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.AppInfo;

@Mapper
public interface AppInfoMapper {
    int create(AppInfo appInfo);
    int upsert(AppInfo appInfo);
    AppInfo findByAppkey(@Param("appkey")String appkey);
}
