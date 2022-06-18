package com.company.repository;

import com.company.entity.VideoLikeEntity;
import com.company.mapper.LikeCountMapper;
import com.company.mapper.VideoLikeInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VideoLikeRepository extends JpaRepository<VideoLikeEntity, Integer> {
    Optional<VideoLikeEntity> findByVideoIdAndProfileId(Integer videoId, Integer pid);

    Page<VideoLikeEntity> findByProfileId(Integer pId, Pageable pageable);

    @Query("SELECT vl.id as vl_id, ch.id as ch_id, ch.name as ch_name, " +
            "v.id as v_id, v.videoName as v_name, v.key as v_key, v.description as v_descrip, v.previewId as v_attach_id " +
            "FROM VideoLikeEntity  as vl " +
            " INNER JOIN vl.video as v " +
            " INNER JOIN v.channel as ch " +
            " INNER JOIN vl.profile as pr " +
            " order by vl.createdDate desc ")
    Page<VideoLikeInfoMapper> getVideoLike(Pageable pageable);

    // @Query("SELECT SUM(total_days) FROM MyEntity") jpquery

    @Query(value = "select sum(case when type = 'Like' THEN 1 else 0 END) like_count," +
            "sum(case when type = 'Dislike' THEN 0 else 1 END) dislike_count " +
            "from video_like " +
            "where video_id = :videoId", nativeQuery = true)
    LikeCountMapper getLikeCountByVideoId(@Param("videoId") Integer videoId);

}
