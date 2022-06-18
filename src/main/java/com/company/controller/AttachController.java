package com.company.controller;

import com.company.dto.AttachDto;
import com.company.enums.ProfileRole;
import com.company.service.AttachService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @ApiOperation(value = "create", notes = "Method for upload attach", nickname = "Mazgi")
    @PostMapping("/public/upload")
    public ResponseEntity<AttachDto> create(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachService.upload(file));
    }

//    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
//    public byte[] open(@PathVariable("fileName") String fileName) {
//        return attachService.open(fileName);
//    }

    @ApiOperation(value = "open_general", notes = "Method for open attach", nickname = "Mazgi")
    @GetMapping(value = "/open_general/{id}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("id") String id) {
        return attachService.open_general(id);
    }

    @ApiOperation(value = "download", notes = "Method for download attach", nickname = "Mazgi")
    @GetMapping("/public/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        return attachService.download(fileName);
    }

    @GetMapping("/adm/list")
    public ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "3") int size,
                                  HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.paginationList(page, size));
    }

    @DeleteMapping("/adm/delete/{key}")
    public ResponseEntity<?> delete(@PathVariable("key") String key, HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.delete(key));
    }
}