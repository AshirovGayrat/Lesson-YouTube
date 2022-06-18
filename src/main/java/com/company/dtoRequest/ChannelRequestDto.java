package com.company.dtoRequest.dto;

import com.company.dto.AttachDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ChannelRequestDto {
    @NotNull
    @Size(min = 3)
    private  String name;
    private  String description;
    @NotNull
    private  String key;
    private  String bannerPhotoId;
    private  String channelPhotoId;
    private  Integer profileId;
}
