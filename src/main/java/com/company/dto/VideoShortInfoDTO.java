package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoShortInfoDTO {
    //{id,name,key,duration}
    private Integer id;
    private String name;
    private String description;
    private String key;
    private Integer duration;
    private String previewAttachId;
}
