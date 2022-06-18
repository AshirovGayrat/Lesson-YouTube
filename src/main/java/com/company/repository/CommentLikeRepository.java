package com.company.repository;

import com.company.entity.CommentLikeEntity;
import com.company.mapper.LikeCountMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {
    @Query(value = "select sum(case when type = 'Like' THEN 1 else 0 END) like_count," +
            "sum(case when type = 'Dislike' THEN 0 else 1 END) dislike_count " +
            "from comment_like " +
            "where comment_id = :commentId", nativeQuery = true)
    LikeCountMapper getLikeCountByCommentId(@Param("commentId") Integer commentId);

    Optional<CommentLikeEntity> findByProfileIdAndCommentId(Integer pId, Integer cId);
}
