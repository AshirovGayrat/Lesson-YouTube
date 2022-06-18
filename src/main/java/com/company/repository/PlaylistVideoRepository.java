package com.company.repository;

import com.company.entity.PlaylistVideoEntity;
import com.company.mapper.PlaylistVideoInfoMapper;
import com.company.mapper.VideoShortInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideoEntity, Integer> {

    @Query("SELECT v.id as v_id, v.videoName as v_name, v.key as v_key " +
            " FROM PlaylistVideoEntity  as pv " +
            " INNER JOIN pv.playlist as p " +
            " INNER JOIN pv.video as v " +
            "where pv.playlistId = :id " +
            " order by pv.orderNum asc ")
    public Page<VideoShortInfoMapper> getVideoList(@Param("id")Integer id, Pageable pageable);

//    @Query(value = " select v.id as v_id, v.video_name as v_name, v.key as v_key " +
//            " from playlist_video as pv " +
//            " inner join video v on v.id = pv.video_id " +
//            " inner join playlist p on p.id = pv.playlist_id " +
//            " where pv.id =:id " +
//            " order by pv.order_num asc ", nativeQuery = true)
//    public Page<VideoShortInfoMapper> getVideoList(@Param("id")Integer id, Pageable pageable);

    //playlist_id,video(id,preview_attach(id,key,url),title,duration),
    //            channel(id,name,key),created_date, order_num

    @Query("SELECT p.id as p_id, v.id as v_id, v.attachId as v_attach_id, " +
            " v.previewId as v_preview_id, v.videoName as v_name, v.key as v_key, v.createdDate as created_date," +
            " ch.id as ch_id, ch.name as ch_name, ch.key as ch_key, " +
            " pv.orderNum as pv_order_num" +
            " FROM PlaylistVideoEntity  as pv " +
            " INNER JOIN pv.playlist as p " +
            " INNER JOIN pv.video as v " +
            " INNER JOIN v.channel as ch" +
            " where pv.playlistId = :id " +
            " and v.status = 'PUBLIC' " +
            " order by pv.orderNum asc ")
    public Page<PlaylistVideoInfoMapper> getPlaylistVideoList(@Param("id")Integer playlistId, Pageable pageable);

}