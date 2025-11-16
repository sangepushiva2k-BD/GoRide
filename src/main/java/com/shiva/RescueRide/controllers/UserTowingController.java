package com.shiva.RescueRide.controllers;

import com.shiva.RescueRide.dto.user.CreateTowingRequestDto;
import com.shiva.RescueRide.dto.user.TowingRequestResponseDto;
import com.shiva.RescueRide.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/requests")
@RequiredArgsConstructor
public class UserTowingController {

    private final UserService userService;

    private String getCurrentUserEmail(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return principal.toString();
    }

    @PostMapping
    public ResponseEntity<TowingRequestResponseDto> createRequest(
            @RequestBody CreateTowingRequestDto dto,
            Authentication authentication
    ) {
        String email = getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.createTowingRequest(email, dto));
    }

    @GetMapping
    public ResponseEntity<List<TowingRequestResponseDto>> getMyRequests(
            Authentication authentication
    ) {
        String email = getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.getMyRequests(email));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TowingRequestResponseDto> cancelRequest(
            @PathVariable String id,
            Authentication authentication
    ) {
        String email = getCurrentUserEmail(authentication);
        return ResponseEntity.ok(userService.cancelRequest(email, id));
    }

}
