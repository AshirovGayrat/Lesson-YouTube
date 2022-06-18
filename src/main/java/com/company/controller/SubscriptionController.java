package com.company.controller;

import com.company.dtoRequest.SubscriptionDTO;
import com.company.dtoRequest.VideoTagRequestDTO;
import com.company.service.SubscriptionService;
import com.company.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/subscription")
@Slf4j
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation(value = "create", notes = "Method for create subscription", nickname = "Mazgi")
    @PostMapping("/public")
    public ResponseEntity<Boolean> create(@RequestBody @Valid SubscriptionDTO dto,
                                          HttpServletRequest request){
        return ResponseEntity.ok(subscriptionService.create(dto, JwtUtil.getIdFromHeader(request)));
    }

    @ApiOperation(value = "changeStatus", notes = "Method for changeStatus subscription", nickname = "Mazgi")
    @PutMapping("/public/status")
    public ResponseEntity<Boolean> changeStatus(@RequestBody @Valid SubscriptionDTO dto,
                                          HttpServletRequest request){
        log.info("Change subscription Status:{}", dto);
        return ResponseEntity.ok(subscriptionService.changeStatusOrType(JwtUtil.getIdFromHeader(request), dto));
    }

    @ApiOperation(value = "changeType", notes = "Method for changeType subscription", nickname = "Mazgi")
    @PutMapping("/public/type")
    public ResponseEntity<Boolean> changeType(@RequestBody @Valid SubscriptionDTO dto,
                                          HttpServletRequest request){
        log.info("Change subscription type:{}", dto);
        return ResponseEntity.ok(subscriptionService.changeStatusOrType(JwtUtil.getIdFromHeader(request), dto));
    }
}
