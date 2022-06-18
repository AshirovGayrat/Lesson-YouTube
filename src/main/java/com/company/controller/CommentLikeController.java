package com.company.controller;

import com.company.service.CommentLikeService;
import com.company.dtoRequest.dto.LikeRequestDTO;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment_like")
@Slf4j
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    @ApiOperation(value = "create", notes = "Method for create Like or Dislike", nickname = "Mazgi")
    @PostMapping("/public/create/{c_id}")
    public ResponseEntity<String> create(@PathVariable("c_id") Integer c_id,
                                         @RequestBody LikeRequestDTO dto,
                                         HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(commentLikeService.create(pid, c_id, dto));
    }

    @ApiOperation(value = "remove", notes = "Method for remove Like or Dislike", nickname = "Mazgi")
    @DeleteMapping("/public/remove/{c_id}")
    public ResponseEntity<Boolean> removeLike(@PathVariable("c_id") Integer c_id,
                                              HttpServletRequest request) {
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(commentLikeService.remove(c_id, pid));
    }
}
