package com.shiva.RescueRide.dto.auth;

import com.shiva.RescueRide.entities.Vehicle;
import com.shiva.RescueRide.enums.AppEnums;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private AppEnums.UserRole role;  // allow USER / DRIVER; ADMIN you can create manually
    private Vehicle vehicle;
}
