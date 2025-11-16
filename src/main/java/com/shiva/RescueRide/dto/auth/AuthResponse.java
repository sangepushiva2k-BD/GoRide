package com.shiva.RescueRide.dto.auth;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String userId;
    private AppEnums.UserRole role;
}
