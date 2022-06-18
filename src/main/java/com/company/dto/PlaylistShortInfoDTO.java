package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaylistShortInfoDTO {
    //id, name,created_date,channel(id,name),video_count,video_list[{id,name,key,duration}] (first 2)
    private Integer id;
    private String name;
    private LocalDateTime createdDate;
    private ChannelShortInfoDTO channel;
    private int videoCount;
    private VideoShortInfoDTO video;
    private List<VideoShortInfoDTO> videoList;

}
