package com.company.repository;

import com.company.entity.CommentEntity;
import com.company.mapper.CommentInfoMapper;
import com.company.mapper.PlaylistShortInfoMapper;
import com.company.mapper.VideoCommentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByProfileId(Integer pId);

    ////id,content,created_date,like_count,dislike_count, video(id,name,preview_attach_id,title,duration)
    @Query("SELECT c.id as c_id,c.content as content, c.createdDate as created_date, " +
            " v.id as v_id, v.videoName as v_name, v.previewId as v_preview_id, v.description as v_description " +
            " FROM CommentEntity  as c " +
            " INNER JOIN c.video as v " +
            " where c.profileId =:pId " +
            " order by c.createdDate desc ")
    public List<CommentInfoMapper> getProfilsCommentList(@Param("pId") Integer pId);


    //   // id,content,created_date,
    //    //        channel(id,key,name,photo(id,url))
    @Query("SELECT c.id as c_id,c.content as content, c.createdDate as created_date, " +
            " p.id as p_id, p.name as p_name, p.surname as p_surname, p.attachId as p_photo_id " +
            " FROM CommentEntity  as c " +
            " INNER JOIN c.video as v " +
            " INNER JOIN c.profile as p " +
            " where v.id =:videoId " +
            " order by c.createdDate desc ")
    public List<VideoCommentMapper> getVideoCommentList(@Param("videoId") Integer videoId);

}