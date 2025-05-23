package com.juggle.chat.models;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Post {
    private Long id;
    private String postId;
    private String title;
    private Byte []content;
    private String contentBrief;
    private Byte []postExset;
    private Integer isDelete;
    private String userId;
    private Long createdTime;
    private Timestamp updatedTime;
    private Integer status;
    private String appkey;
}
