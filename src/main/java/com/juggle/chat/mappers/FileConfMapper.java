package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.FileConf;

@Mapper
public interface FileConfMapper {
    int upsert(FileConf fileConf);
    FileConf findEnableFileConf(@Param("appkey")String appkey);
    List<FileConf> findFileConfs(@Param("appkey")String appkey);
    FileConf findFileConf(@Param("appkey")String appkey, @Param("channel")String channel);
    int updateEnable(@Param("appkey")String appkey, @Param("channel")String channel);
}
