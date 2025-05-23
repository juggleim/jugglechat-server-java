package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostComment {
    private Long id;
    private String commentId;
    private String postId;
    private String parentCommentId;
    private String parentUserId;
    private String text;
    private Integer isDelete;
    private String userId;
    private Long createdTime;
    private Timestamp updatedTime;
    private Integer status;
    private String appkey;
}
