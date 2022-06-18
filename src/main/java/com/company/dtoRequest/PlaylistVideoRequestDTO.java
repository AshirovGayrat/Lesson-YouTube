package com.company.dtoRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoRequestDTO {
    private Integer playlistId;
    private Integer videoId;
    private int orderNum;
}
