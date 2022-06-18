package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponceDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private String content;
    private Integer profileId;
    private Integer videoId;
    private Integer commentId;
}
