package com.company.controller;

import com.company.dtoRequest.TagRequestDTO;
import com.company.dto.TagResponceDTO;
import com.company.enums.ProfileRole;
import com.company.service.TagService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "create", notes = "Method for create tag", nickname = "Mazgi")
    @PostMapping("/public")
    public ResponseEntity<?> create(@RequestBody TagRequestDTO dto, HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        try {
            return ResponseEntity.ok(tagService.create(dto));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Already exists: " + e.getMessage());
        }
    }

    @ApiOperation(value = "update", notes = "Method for update tag", nickname = "Mazgi")
    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody TagRequestDTO dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.delete(id));
    }

    @GetMapping("/adm")
    public ResponseEntity<List<TagResponceDTO>> tagList(HttpServletRequest request){
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.getTagList());
    }

}
