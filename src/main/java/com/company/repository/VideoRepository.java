package com.company.repository;

import com.company.entity.VideoEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {
    Optional<VideoEntity> findByKey(String key);

    Page<VideoEntity> findAllByChannelId(Integer channelId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update VideoEntity set viewCount = :vCount where id = :id")
    int updateViewCount(@Param("vCount") int vCount, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update VideoEntity set sharedCount = :sharedCount where id = :id")
    int updateShareCount(@Param("sharedCount") long sharedCount, @Param("id") Integer id);
}