package com.company.controller;

import com.company.dto.LikeAndDislikeCountDTO;
import com.company.dtoRequest.dto.LikeRequestDTO;
import com.company.dto.VideoLikeResponceDTO;
import com.company.service.VideoLikeService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/video_like")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;

    @ApiOperation(value = "create", notes = "Method for create Like or Dislike", nickname = "Mazgi")
    @PostMapping("/public/create/{videoId}")
    public ResponseEntity<String> create(@PathVariable("videoId") Integer videoId,
                                         @RequestBody LikeRequestDTO dto,
                                         HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoLikeService.create(pid, videoId, dto));
    }

    @ApiOperation(value = "remove", notes = "Method for remove Like or Dislike", nickname = "Mazgi")
    @DeleteMapping("/public/remove/{videoId}")
    public ResponseEntity<Boolean> removeLike(@PathVariable("videoId") Integer videoId,
                                              HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoLikeService.remove(videoId, pid));
    }

    @ApiOperation(value = "get", notes = "Method for get user liked Videos", nickname = "Mazgi")
    @GetMapping("/public/get")
    public ResponseEntity<PageImpl<VideoLikeResponceDTO>> getUserLicedVideoList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                                                HttpServletRequest request) {
        return ResponseEntity.ok(videoLikeService.getUserLicedVideoList(JwtUtil.getIdFromHeader(request), page, size));
    }

    @GetMapping("/public/{v_id}")
    public ResponseEntity<LikeAndDislikeCountDTO> getLikeDislike(@PathVariable("v_id")Integer vId,
                                                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoLikeService.getLikeAndDislikeCount(vId));
    }

}
