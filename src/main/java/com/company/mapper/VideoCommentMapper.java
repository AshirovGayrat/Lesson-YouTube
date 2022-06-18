package com.company.mapper;

import java.time.LocalDateTime;

public interface VideoCommentMapper {
    //  c_id, content,  created_date, " +
    //  p_id, p_name, p_surname,  p_photo_id

    Integer getC_id();
    String getContent();
    LocalDateTime getCreated_date();

    Integer getP_id();
    String getP_name();
    String getP_surname();
    String getP_photo_id();

}
