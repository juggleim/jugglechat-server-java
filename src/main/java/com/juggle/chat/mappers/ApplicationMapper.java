package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.Application;

@Mapper
public interface ApplicationMapper {
    int create(Application application);
    int update(Application application);
    int batchDelete(@Param("appkey") String appkey, @Param("appIds") List<String> appIds);
    Application findByAppId(@Param("appkey") String appkey, @Param("appId") String appId);
    List<Application> qryApplications(@Param("appkey") String appkey, @Param("limit") long limit);
    List<Application> qryApplicationsByPage(@Param("appkey") String appkey, @Param("page") long page, @Param("size") long size);
}
