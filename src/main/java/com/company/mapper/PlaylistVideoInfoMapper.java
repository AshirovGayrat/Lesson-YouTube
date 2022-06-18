package com.company.mapper;

import java.time.LocalDateTime;

public interface PlaylistVideoInfoMapper {
    //p_id,  v_id,  v_attach_id, " +
    //   v_preview_id,  v_name,  v_key,  created_date," +
    //  ch_id,  ch_name,  ch_key, " +
    //    pv_order_num

    Integer getP_id();
    Integer getV_id();
    String getV_attach_id();
    String getV_preview_id();
    String getV_name();
    String getV_key();
    LocalDateTime getCreated_date();
    Integer getCh_id();
    String getCh_name();
    String getCh_key();

    Integer getPv_order_num();
}
