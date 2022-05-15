package com.company.controller;

import com.company.dto.AttachDto;
import com.company.dto.ProfileDto;
import com.company.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.createProfile(dto));
    }

    @GetMapping("/adm")
    public ResponseEntity<?> getPaginationList(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(profileService.paginationList(page, size));
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Integer id,
                                           @RequestBody @Valid ProfileDto dto) {
        log.info("update profile: {}", "id: " + id + " " + dto);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.delete(id));
    }


    /////// TODO
    @PutMapping("/public/{pid}")
    public ResponseEntity<?> updateImage(@RequestParam MultipartFile file,
                                         @PathVariable("pid") Integer pid) {
        log.info("update profile: {}", "pid: " + pid);
        try {
            return ResponseEntity.ok(profileService.updateImage(file, pid));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Attach not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Integer pid) {
        return ResponseEntity.ok(profileService.deleteImage(pid));
    }

}
