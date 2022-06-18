package com.company.mapper;

import java.time.LocalDateTime;

public interface PlaylistShortInfoMapper {
    //p.id as p_id,p.name as p_name, p.createdDate as p_created_date, " +
    //            " ch.id as ch_id, ch.name as ch_name "
    Integer p_id();
    String p_name();
    LocalDateTime p_created_date();
    Integer ch_id();
    String ch_name();
}
