package com.company.controller;

import com.company.dtoRequest.ChangePswdDTO;
import com.company.dto.ProfileDto;
import com.company.dtoRequest.UpdateProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @ApiOperation(value = "create", notes = "Method for create profile", nickname = "Mazgi")
    @PostMapping("/adm")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDto dto,
                                           HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.createProfile(dto));
    }

    @ApiOperation(value = "changePswd", notes = "Method for changePswd profile", nickname = "Mazgi")
    @PutMapping("/change/pswd")
    public ResponseEntity<?> changePswd(@RequestBody ChangePswdDTO dto,
                                        HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.changePswd(dto));
    }

    @ApiOperation(value = "update", notes = "Method for update profile", nickname = "Mazgi")
    @PutMapping("/public")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid UpdateProfileDTO dto,
                                           HttpServletRequest request) {
        Integer id=JwtUtil.getIdFromHeader(request);
        log.info("update profile: {}", "id: " + id + " " + dto);
        return ResponseEntity.ok(profileService.updateProfile(id, dto));
    }

    @ApiOperation(value = "update", notes = "Method for update profile Image", nickname = "Mazgi")
    @PutMapping("/public/{file}")
    public ResponseEntity<?> updateImage(@RequestParam MultipartFile file,
                                         HttpServletRequest request) {
        Integer pid=JwtUtil.getIdFromHeader(request);
        log.info("update profile: {}", "pid: " + pid);
        try {
            return ResponseEntity.ok(profileService.updateImage(file, pid));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Attach not found");
        }
    }

    @DeleteMapping("/public/delete")
    public ResponseEntity<?> deleteImage(HttpServletRequest request) {
        Integer pid=JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.deleteImage(pid));
    }


    // ADMIN
    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Integer id,
                                           HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }

    @GetMapping("/adm")
    public ResponseEntity<?> getPaginationList(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "3") int size,
                                               HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.paginationList(page, size));
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getById(id));
    }

}
