package com.company.dtoRequest;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentRequestDTO {
    @NotNull
    private String content;
    private Integer profileId;
    private Integer videoId;
    private Integer commentId;
}
