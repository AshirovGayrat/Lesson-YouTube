package com.company.dtoRequest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VideoRequestDTO {
    @NotNull
    private String key;
    @NotNull
    private String videoName;
    private String description;
    private String previewPhotoId;
    private String attachId;
    private Integer channelId;
    private Integer playlistId;
    private Integer categoryId;
    private String type;
}
