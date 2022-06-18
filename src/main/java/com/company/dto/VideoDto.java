package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {
    private int viewCount;
    private LocalDateTime createdDate;
    private Integer id;
    private String key;
    private String videoName;
    private String description;
    private AttachSimpleDTO previewPhoto;
    private ChannelShortInfoDTO channelDto;
    private Integer playlistId;
}
