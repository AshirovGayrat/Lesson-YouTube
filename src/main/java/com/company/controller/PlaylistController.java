package com.company.controller;

import com.company.dtoRequest.PlaylistRequestDTO;
import com.company.enums.ProfileRole;
import com.company.service.PlaylistService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @ApiOperation(value = "create", notes = "Method for create playlist", nickname = "Mazgi")
    @PostMapping("/public/{ch_id}")
    public ResponseEntity<?> create(@PathVariable("ch_id") Integer chId,
                                    @RequestBody PlaylistRequestDTO dto,
                                    HttpServletRequest request) {
        return ResponseEntity.ok(playlistService.create(chId, dto, JwtUtil.getIdFromHeader(request)));
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0")int page,
                                    @RequestParam(value = "size", defaultValue = "5")int size,
                                    HttpServletRequest request){
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(playlistService.getAllWithPagination(page, size));
    }

    @GetMapping("/public/users")
    public ResponseEntity<?> getPlaylistsByUser(@RequestParam(value = "page", defaultValue = "0")int page,
                                    @RequestParam(value = "size", defaultValue = "20")int size,
                                    HttpServletRequest request){
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(playlistService.list(page, size));
    }

    @ApiOperation(value = "getChannelPlaylists", notes = "Method for get playlist by channel key", nickname = "Mazgi")
    @GetMapping("/public/channels/{ch_key}")
    public ResponseEntity<?> getChannelPlaylists(@PathVariable("ch_key")String chKey,
                                                 @RequestParam(value = "page", defaultValue = "0")int page,
                                    @RequestParam(value = "size", defaultValue = "20")int size,
                                    HttpServletRequest request){
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(playlistService.channelPlaylists(page, size, chKey));
    }

    @ApiOperation(value = "update", notes = "Method for update playlist", nickname = "Mazgi")
    @PutMapping("/public/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody PlaylistRequestDTO dto) {
        return ResponseEntity.ok(playlistService.update(id, dto));
    }

    @DeleteMapping("/public/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(playlistService.delete(id));
    }
}
