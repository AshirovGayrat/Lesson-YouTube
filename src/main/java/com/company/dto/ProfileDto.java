package com.company.dto;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDto  {

    private Integer id;
    @NotNull
    @Size(min = 3, max = 15, message = "name size min=3 , max=15")
    private String name;
    @NotNull
    @Size(min = 3, max = 15, message = "name size min=3 , max=15")
    private String surname;
    @Email
    private String email;
    @NotNull
    @Size(min = 3, max = 15, message = "name size min=3 , max=15")
    private String password;

    private ProfileStatus status;
    private ProfileRole role;

    private Integer photoId;

    private String jwt;

    private LocalDateTime createDate=LocalDateTime.now();
    private LocalDateTime updateDate;

    private AttachSimpleDTO attachDto;
}
