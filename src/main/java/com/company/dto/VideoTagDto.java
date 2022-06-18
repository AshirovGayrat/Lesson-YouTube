package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoTagDto extends BaseDTO{
    private Integer tagId;
    private Integer videoId;
}
