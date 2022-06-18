package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoCommentResponceDTO extends BaseDTO{
    // id,content,created_date,like_count,dislike_count,
    //        profile(id,name,surname,photo(id,url))
    private String content;
    private int likeCount;
    private int disLikeCount;

    private  ProfileSimpleDTO profile;
}
