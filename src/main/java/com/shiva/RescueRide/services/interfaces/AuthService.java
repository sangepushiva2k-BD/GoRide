package com.shiva.RescueRide.services.interfaces;

import com.shiva.RescueRide.dto.auth.AuthResponse;
import com.shiva.RescueRide.dto.auth.LoginRequest;
import com.shiva.RescueRide.dto.auth.RegisterRequest;
import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.enums.AppEnums;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    List<User> getAllUsers();

    String deleteAllUsers();

//    String updateUserStatus(String userId, AppEnums.DriverStatus status);
}
