package com.company.controller;

import com.company.dto.PlaylistVideoInfoDTO;
import com.company.dtoRequest.PlaylistVideoRequestDTO;
import com.company.service.PlaylistVideoService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/playlist_video")
public class PlaylistVideoController {
    @Autowired
    private PlaylistVideoService playlistVideoService;

    @ApiOperation(value = "create", notes = "Method for create playlistVideo", nickname = "Mazgi")
    @PostMapping("/public")
    public ResponseEntity<?> create(@RequestBody PlaylistVideoRequestDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(playlistVideoService.create(dto));
    }

    @ApiOperation(value = "getVideos", notes = "Method for get Videos by playlist id", nickname = "Mazgi")
    @GetMapping("/public/{id}")
    public ResponseEntity<PageImpl<PlaylistVideoInfoDTO>> getVideoListByPlaylistId(@PathVariable("id") Integer playlistId,
                                                                                   @RequestParam(value = "page",defaultValue = "0")int page,
                                                                                   @RequestParam(value = "size", defaultValue = "10")int size,
                                                                                   HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(playlistVideoService.getVideoList(playlistId, page, size));
    }

    @ApiOperation(value = "update", notes = "Method for update playlistVideo", nickname = "Mazgi")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PlaylistVideoRequestDTO dto) {
        return ResponseEntity.ok(playlistVideoService.update(id, dto));
    }

    @DeleteMapping("/public/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(playlistVideoService.delete(id));
    }
}
