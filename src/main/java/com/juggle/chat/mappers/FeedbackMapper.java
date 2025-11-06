package com.juggle.chat.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.juggle.chat.models.Feedback;

@Mapper
public interface FeedbackMapper {
    int create(Feedback feedback);
}
