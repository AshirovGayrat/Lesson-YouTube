package com.company.repository;

import com.company.entity.ChannelEntity;
import com.company.enums.ChannelStatus;
import com.company.enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Integer> {

//    Optional<ChannelEntity> findByName(String name);
//
//    Optional<ChannelEntity> findByKey(String key);

    Optional<ChannelEntity> findByProfileId(Integer id);

    @Query(value = "select name from channel where name =:name", nativeQuery = true)
    String getByName(@Param("name") String name);

    @Query(value = "select name from channel where name =:key", nativeQuery = true)
    String getByKey(@Param("key") String key);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set status = :status where id = :id")
    int updateStatus(@Param("status") ChannelStatus status, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set channelPhotoId = :attachId where id = :id")
    int updatePhoto(@Param("attachId") String attachId, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ChannelEntity set bannerPhotoId = :attachId where id = :id")
    int updateBanner(@Param("attachId") String attachId, @Param("id") Integer id);
}