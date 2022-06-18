package com.company.repository;

import com.company.entity.PlaylistEntity;
import com.company.mapper.PlayListInfoJpqlAdminMapper;
import com.company.mapper.PlaylistShortInfoMapper;
import com.company.mapper.VideoShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer> {

    @Query("SELECT pl.id as pl_id,pl.name as pl_name,pl.description as pl_description,pl.status as pl_status,pl.orderNum as pl_order_num, " +
            "ch.id as ch_id, ch.name as ch_name, ch.channelPhotoId as ch_photo_id, " +
            "pr.id as pr_id, pr.name as pr_name,pr.surname as pr_surname,pr.attachId as pr_photo_id " +
            "FROM PlaylistEntity  as pl " +
            " INNER JOIN pl.channel as ch " +
            " INNER JOIN ch.profile as pr " +
            " order by pl.createdDate desc ")
    public Page<PlayListInfoJpqlAdminMapper> getPlaylistInfoJpql(Pageable pageable);

    @Query("SELECT p.id as p_id,p.name as p_name, p.createdDate as p_created_date, " +
            " ch.id as ch_id, ch.name as ch_name " +
            " FROM PlaylistEntity  as p " +
            " INNER JOIN p.channel as ch " +
            " where ch.key =:key" +
            " order by p.orderNum desc ")
    public Page<PlaylistShortInfoMapper> getChannelPlaylists(@Param("key") String key, Pageable pageable);

    //id, name,created_date,channel(id,name),video_count,video_list[{id,name,key,duration}] (first 2)
}