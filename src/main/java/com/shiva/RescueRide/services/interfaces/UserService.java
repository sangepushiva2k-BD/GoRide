package com.shiva.RescueRide.services.interfaces;

import com.shiva.RescueRide.dto.user.CreateTowingRequestDto;
import com.shiva.RescueRide.dto.user.TowingRequestResponseDto;

import java.util.List;

public interface UserService {

    TowingRequestResponseDto createTowingRequest(String userEmail, CreateTowingRequestDto dto);

    List<TowingRequestResponseDto> getMyRequests(String userEmail);

    TowingRequestResponseDto cancelRequest(String userEmail, String requestId);
}
