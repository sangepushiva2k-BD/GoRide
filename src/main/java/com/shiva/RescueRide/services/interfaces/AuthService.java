package com.shiva.RescueRide.services.interfaces;

import com.shiva.RescueRide.dto.auth.AuthResponse;
import com.shiva.RescueRide.dto.auth.LoginRequest;
import com.shiva.RescueRide.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
