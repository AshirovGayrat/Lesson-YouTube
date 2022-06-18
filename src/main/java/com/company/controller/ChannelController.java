package com.company.controller;

import com.company.dto.AttachDto;
import com.company.dtoRequest.dto.ChannelRequestDto;
import com.company.dtoRequest.ChannelUpdateStatusDTO;
import com.company.enums.ProfileRole;
import com.company.service.ChannelService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @ApiOperation(value = "create", notes = "Method for create channel", nickname = "Mazgi")
    @PostMapping("/public")
    public ResponseEntity<?> create(@RequestBody @Valid ChannelRequestDto dto,
                                    HttpServletRequest request) {
        log.info("update profile: {}", "dto: " + dto);
        return ResponseEntity.ok(channelService.create(dto, JwtUtil.getIdFromHeader(request)));
    }

    @ApiOperation(value = "getAll", notes = "Method for get channel pagination", nickname = "Mazgi")
    @GetMapping("/adm/pagination")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(channelService.getAllWithPagination(page, size));
    }

    @ApiOperation(value = "update", notes = "Method for update channel", nickname = "Mazgi")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ChannelRequestDto dto,
                                    HttpServletRequest request) {
        log.info("update profile: {}", "dto: " + dto);
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(channelService.update(pid, id, dto));
    }

    @PostMapping("/public/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Integer id,
                                    @RequestBody ChannelUpdateStatusDTO dto,
                                    HttpServletRequest request) {
        log.info("update profile: {}", "dto: " + dto);
        Integer pid = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(channelService.updateStatus(id, dto));
    }

    @ApiOperation(value = "update", notes = "Method for update Banner Image", nickname = "Mazgi")
    @PutMapping("/public/banner/{ch_id}")
    public ResponseEntity<?> updateBanner(@PathVariable("ch_id")Integer chId,
                                          @RequestBody AttachDto dto,
                                         HttpServletRequest request) {
        try {
            return ResponseEntity.ok(channelService.updateBannerImage(dto.getId(), chId, JwtUtil.getIdFromHeader(request)));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Attach not found");
        }
    }

    @ApiOperation(value = "update", notes = "Method for update channel Image", nickname = "Mazgi")
    @PutMapping("/public/channel/{ch_id}")
    public ResponseEntity<?> updateChannelImage(@PathVariable("ch_id")Integer chId,
                                                @RequestBody AttachDto dto,
                                         HttpServletRequest request) {
        try {
            return ResponseEntity.ok(channelService.updateChannelImage(dto.getId(), chId, JwtUtil.getIdFromHeader(request)));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Attach not found!!!");
        }
    }

    @DeleteMapping("/public/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(channelService.delete(id));
    }
}
