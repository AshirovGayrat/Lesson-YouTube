package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDto extends BaseDTO{
    private String name;
    private String descripyion;
    private int orderNum;
    private AttachSimpleDTO playlistPhotoDto;
    private ChannelResponceDTO channelDto;
}
