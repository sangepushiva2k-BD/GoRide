package com.shiva.RescueRide.services.impl;

import com.shiva.RescueRide.dto.auth.AuthResponse;
import com.shiva.RescueRide.dto.auth.LoginRequest;
import com.shiva.RescueRide.dto.auth.RegisterRequest;
import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.enums.AppEnums;
import com.shiva.RescueRide.exceptions.BadRequestException;
import com.shiva.RescueRide.exceptions.ResourceNotFoundException;
import com.shiva.RescueRide.repositories.UserRepository;
import com.shiva.RescueRide.repositories.VehicleRepository;
import com.shiva.RescueRide.security.CustomUserDetails;
import com.shiva.RescueRide.security.JwtUtil;
import com.shiva.RescueRide.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    VehicleRepository vehicleRepo;


    @Override
    public AuthResponse register(RegisterRequest request) {

        userRepo.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new BadRequestException("Email already registered");
                });

        if(request.getVehicle()!=null){
            request.getVehicle().setId(UUID.randomUUID());
            vehicleRepo.save(request.getVehicle());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .vehicle(request.getVehicle())
                .status(AppEnums.DriverStatus.AVAILABLE)
                .build();

        user = userRepo.save(user);



        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return new AuthResponse(token, user.getId(), user.getRole(),user.getVehicle(),user.getStatus());
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

        return new AuthResponse(token, userDetails.getId(), userDetails.getRole(),userDetails.getVehicle(),userDetails.getStatus());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String deleteAllUsers() {
        userRepo.deleteAll();
        return "Deleted Successfully";
    }

//    @Override
    public String updateUserStatus(String id, AppEnums.DriverStatus status){
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setStatus(status);
        userRepo.save(user);
        return "Updated Successfully";
    }
}
