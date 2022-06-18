package com.company.dtoRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Slf4j
public class VideoTagRequestDTO {
    @NotNull
    private Integer tagId;
    @NotNull
    private Integer videoId;
}
