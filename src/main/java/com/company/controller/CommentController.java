package com.company.controller;

import com.company.dto.CommentInfoDTO;
import com.company.dto.CommentResponceDTO;
import com.company.dto.VideoCommentResponceDTO;
import com.company.dtoRequest.CommentRequestDTO;
import com.company.enums.ProfileRole;
import com.company.service.CommentService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "create", notes = "Method for create comment", nickname = "Mazgi")
    @PostMapping("/public")
    public ResponseEntity<?> create(@RequestBody CommentRequestDTO dto, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(commentService.create(dto, JwtUtil.getIdFromHeader(request)));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Already exists: " + e.getMessage());
        }
    }

    @ApiOperation(value = "update", notes = "Method for update comment", nickname = "Mazgi")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CommentRequestDTO dto,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(commentService.update(id, JwtUtil.getIdFromHeader(request), dto));
    }

    @DeleteMapping("/public/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(commentService.delete(id));
    }

    @ApiOperation(value = "profilsCommentList", notes = "Method for get profils comment list", nickname = "Mazgi")
    @GetMapping("/public")
    public ResponseEntity<List<CommentInfoDTO>> profilsCommentList(HttpServletRequest request) {
        return ResponseEntity.ok(commentService.getProfilsCommentList(JwtUtil.getIdFromHeader(request)));
    }

    @ApiOperation(value = "getCommentsByVideoId", notes = "Method for get comment list by video id", nickname = "Mazgi")
    @GetMapping("/public/{id}")
    public ResponseEntity<List<VideoCommentResponceDTO>> getCommentsByVideoId(@PathVariable("id")Integer videoId,
                                                                              HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(commentService.getCommentListByVideoId(videoId));
    }

    //TODO comment`s comments


    /*
    METHODS FOR ADMIN
    */
    @GetMapping("/adm/list")
    public ResponseEntity<PageImpl<CommentResponceDTO>> commentList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(commentService.getCommentList(page, size));
    }

    @GetMapping("/adm/{pid}")
    public ResponseEntity<List<CommentResponceDTO>> commentListByPid(@PathVariable("pid") Integer pId,
                                                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(commentService.getCommentsByPid(pId));
    }


}
