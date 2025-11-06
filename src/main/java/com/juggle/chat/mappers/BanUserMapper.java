package com.juggle.chat.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juggle.chat.models.BanUser;

@Mapper
public interface BanUserMapper {
    int upsert(BanUser banUser);
    List<BanUser> findByUserId(@Param("appkey") String appkey, @Param("userId") String userId);
    int delBanUser(@Param("appkey") String appkey, @Param("userId") String userId, @Param("scopeKey") String scopeKey);
    int cleanBaseTime(@Param("appkey") String appkey, @Param("userId") String userId, @Param("endTime") long endTime);
    List<BanUser> qryBanUsers(@Param("appkey") String appkey, @Param("limit") long limit, @Param("startId") long startId);
}
