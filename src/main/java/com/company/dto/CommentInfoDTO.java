package com.company.dto;

import lombok.Data;


@Data
public class CommentInfoDTO extends BaseDTO{
    //id,content,created_date,like_count,dislike_count, video(id,name,preview_attach_id,title,duration)
    private String content;
    private int likeCount;
    private int disLikeCount;

    private VideoShortInfoDTO video;
}
