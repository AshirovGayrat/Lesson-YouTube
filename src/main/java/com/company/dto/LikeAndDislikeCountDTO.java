package com.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeAndDislikeCountDTO {
    private Integer likeCount;
    private Integer disLikeCount;

    public LikeAndDislikeCountDTO(Integer likeCount, Integer disLikeCount) {
        this.likeCount = likeCount;
        this.disLikeCount = disLikeCount;
    }
}
