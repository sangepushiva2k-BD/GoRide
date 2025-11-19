package com.shiva.RescueRide.controllers;

import com.shiva.RescueRide.Common;
import com.shiva.RescueRide.dto.auth.AuthResponse;
import com.shiva.RescueRide.dto.auth.LoginRequest;
import com.shiva.RescueRide.dto.auth.RegisterRequest;
import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.enums.AppEnums;
import com.shiva.RescueRide.services.impl.AuthServiceImpl;
import com.shiva.RescueRide.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private  AuthService authService;

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/get-all/users")
    public List<User> getAllUsers(Authentication req) {
        return authService.getAllUsers();
    }

    @DeleteMapping("/delete-all/users")
    public String deleteAllUsers(Authentication req) {
        return authService.deleteAllUsers();
    }

    @PutMapping("/user/change-status")
    public String changeStatus(@RequestParam String userId, @RequestParam AppEnums.DriverStatus status,Authentication req) {
        return authServiceImpl.updateUserStatus(userId,status);
    }
}
