package com.juggle.chat.mappers;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.juggle.chat.models.User;

@Mapper
public interface UserMapper {
    User findByUserId(@Param("appkey") String appkey, @Param("userId") String userId);
    List<User> findByUserIds(@Param("appkey") String appkey, @Param("userIds") List<String> userIds);
    User findByPhone(@Param("appkey") String appkey, @Param("phone")String phone);
    User findByEmail(@Param("appkey") String appkey, @Param("email")String email);
    int create(User user);
    int upsert(User user);
    int update(@Param("appkey") String appkey,@Param("userId")String userId, @Param("nickname")String nickname,@Param("userPortrait")String userPortrait);
    int count(@Param("appkey")String appkey);
    int countByTime(@Param("appkey")String appkey, @Param("start")Timestamp start,@Param("end")Timestamp end);
}
