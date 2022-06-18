package com.company.mapper;

import java.time.LocalDateTime;

public interface CommentInfoMapper {
    // c_id, content,  created_date, " +
    // v_id,  v_name,  v_preview_id,  v_description

    Integer getC_id();
    String getContent();
    LocalDateTime getCreated_date();
    Integer getV_id();
    String getV_name();
    String getV_preview_id();
    String getV_description();
}
