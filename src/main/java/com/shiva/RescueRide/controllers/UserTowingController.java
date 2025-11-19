package com.shiva.RescueRide.controllers;

import com.shiva.RescueRide.Common;
import com.shiva.RescueRide.dto.user.CreateTowingRequestDto;
import com.shiva.RescueRide.dto.user.TowingRequestResponseDto;
import com.shiva.RescueRide.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class UserTowingController {

    @Autowired
    private  UserService userService;


    @PostMapping("/request")
    public ResponseEntity<TowingRequestResponseDto> createRequest(@RequestBody CreateTowingRequestDto dto, Authentication authentication
    ) {
        String email = Common.getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.createTowingRequest(email, dto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TowingRequestResponseDto>> getMyRequests( Authentication authentication
    ) {
        String email = Common.getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.getMyRequests(email));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TowingRequestResponseDto> cancelRequest(
            @PathVariable String id,
            Authentication authentication
    ) {
        String email = Common.getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.cancelRequest(email, id));
    }

}
