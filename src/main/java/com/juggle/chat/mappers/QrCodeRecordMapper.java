package com.juggle.chat.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.QrCodeRecord;

@Mapper
public interface QrCodeRecordMapper {
    int create(QrCodeRecord record);
    QrCodeRecord findById(@Param("appkey")String appkey, @Param("codeId")String codeId);
    int updateStatus(@Param("appkey")String appkey, @Param("codeId")String codeId,@Param("status")int status, @Param("userId")String userId);
}
