package com.company.controller;

import com.company.dtoRequest.VideoTagRequestDTO;
import com.company.service.VideoTagService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/video-tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PostMapping("/public")
    public ResponseEntity<Boolean> addTag(@RequestBody @Valid VideoTagRequestDTO dto,
                                          HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoTagService.addTag(dto));
    }

    @DeleteMapping("/public{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestBody @Valid VideoTagRequestDTO dto,
                                          HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(videoTagService.delete(id, dto));
    }
}
