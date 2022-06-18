package com.company.controller;

import com.company.dto.AuthDto;
import com.company.dto.ProfileDto;
import com.company.dto.RegistrationDto;
import com.company.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "For Authorization")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "login", notes = "Method for login", nickname = "Mazgi")
    @PostMapping("/login")
    public ResponseEntity<ProfileDto> login(@RequestBody @Valid AuthDto dto){
        log.info("login: {}", dto);
        return ResponseEntity.ok(authService.login(dto));
    }

    @ApiOperation(value = "registration", notes = "Method for registration", nickname = "Mazgi")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDto dto){
        log.info("registration: {}", dto);
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "verification", notes = "Method for verification", nickname = "Mazgi")
    @GetMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt){
        log.info("verification: {}", jwt);
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }
}
