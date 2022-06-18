package com.company.repository;

import com.company.entity.VideoTagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoTagRepository extends JpaRepository<VideoTagEntity, Integer> {
    Page<VideoTagEntity> findAllByTagId(Integer id, Pageable pageable);
}