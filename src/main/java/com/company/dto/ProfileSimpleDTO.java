package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSimpleDTO {
    // profile(id,name,surname,photo(id,url))
    private Integer id;
    private String name;
    private String surName;
    private AttachSimpleDTO photo;
}
