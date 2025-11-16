package com.shiva.RescueRide.services.impl;

import com.shiva.RescueRide.dto.auth.AuthResponse;
import com.shiva.RescueRide.dto.auth.LoginRequest;
import com.shiva.RescueRide.dto.auth.RegisterRequest;
import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.exceptions.BadRequestException;
import com.shiva.RescueRide.repositories.UserRepository;
import com.shiva.RescueRide.security.CustomUserDetails;
import com.shiva.RescueRide.security.JwtUtil;
import com.shiva.RescueRide.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        userRepo.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new BadRequestException("Email already registered");
                });

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        user = userRepo.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getId(), user.getRole());
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        String token = jwtUtil.generateToken(
                userDetails.getUsername(),
                userDetails.getRole().name()
        );

        return new AuthResponse(token, userDetails.getId(), userDetails.getRole());
    }
}
