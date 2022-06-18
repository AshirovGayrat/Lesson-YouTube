package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoResponceDTO extends BaseDTO{
    private Integer playlistId;
    private Integer videoId;
    private int orderNum;
}
