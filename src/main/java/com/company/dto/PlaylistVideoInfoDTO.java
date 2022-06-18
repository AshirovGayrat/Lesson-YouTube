package com.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlaylistVideoInfoDTO {
    private Integer playlistId;
    private VideoShortInfoDTO video;
    private ChannelShortInfoDTO channel;
    private LocalDateTime createdDate;
    private int orderNum;
}
