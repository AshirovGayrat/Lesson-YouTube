package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelResponceDTO extends BaseDTO{
    private  String name;
    private  String description;
    private  String key;
    private AttachSimpleDTO bannerPhotoDto;
    private AttachSimpleDTO channelPhotoDto;
    private  Integer profileId;
    private ProfileDto profile;
}
