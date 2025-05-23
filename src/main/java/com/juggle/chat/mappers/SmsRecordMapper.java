package com.juggle.chat.mappers;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.SmsRecord;

@Mapper
public interface SmsRecordMapper {
    int create(SmsRecord record);
    SmsRecord findByPhoneCode(@Param("appkey")String appkey, @Param("phone")String phone, @Param("code")String code);
    SmsRecord findByPhone(@Param("appkey")String appkey, @Param("phone")String phone, @Param("start")Timestamp start);
}
