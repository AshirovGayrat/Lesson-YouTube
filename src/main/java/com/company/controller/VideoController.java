package com.company.controller;

import com.company.dto.VideoDto;
import com.company.dtoRequest.VideoRequestDTO;
import com.company.enums.ProfileRole;
import com.company.service.VideoService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "create", notes = "Method for create video", nickname = "Mazgi")
    @PostMapping("/public/{ch_id}")
    public ResponseEntity<?> create(@PathVariable("ch_id") Integer chId,
                                    @RequestBody VideoRequestDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoService.create(dto, chId));
    }

    @ApiOperation(value = "getAll", notes = "Method for getAll video", nickname = "Mazgi")
    @GetMapping("/public/pagination/{tag_id}")
    public ResponseEntity<?> searchVideosByTagId(@PathVariable("tag_id") Integer tId,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "5") int size,
                                              HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoService.searchByTagId(tId, page, size));
    }

    @ApiOperation(value = "getByKey", notes = "Method for get video by key", nickname = "Mazgi")
    @GetMapping("/public/{key}")
    public ResponseEntity<VideoDto> getByKey(@PathVariable("key") String id, HttpServletRequest request) {
        return ResponseEntity.ok(videoService.getVideoByKey(id, JwtUtil.getIdFromHeader(request)));
    }

    @ApiOperation(value = "update", notes = "Method for update video", nickname = "Mazgi")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody VideoRequestDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoService.update(id, dto));
    }

    @ApiOperation(value = "viewCount", notes = "Method for Increase video view count", nickname = "Mazgi")
    @PutMapping("/public/view/{v_id}")
    public ResponseEntity<?> viewCount(@PathVariable("v_id") Integer vId,
                                       HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        videoService.viewCount(vId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "shareCount", notes = "Method for Increase video share count", nickname = "Mazgi")
    @PutMapping("/public/share/{v_id}")
    public ResponseEntity<?> shareCount(@PathVariable("v_id") Integer vId,
                                        HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        videoService.shareCount(vId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/public/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoService.delete(id));
    }

    @ApiOperation(value = "getChannelVideos", notes = "Method for get channel videos", nickname = "Mazgi")
    @GetMapping("/public/videos/{ch_id}")
    public ResponseEntity<?> getChannelVideos(@PathVariable("ch_id")Integer channelId,
                                              @RequestParam(value = "page", defaultValue = "0")int page,
                                              @RequestParam(value = "size", defaultValue = "5")int size,
                                              HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoService.getChannelVideos(channelId, page, size));
    }

    /*ADMIN*/
    @ApiOperation(value = "getAll", notes = "Method for getAll video", nickname = "Mazgi")
    @GetMapping("/adm/pagination")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(videoService.getAllWithPagination(page, size));
    }

}
