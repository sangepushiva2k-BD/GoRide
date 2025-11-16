package com.shiva.RescueRide.services.impl;

import com.shiva.RescueRide.dto.user.CreateTowingRequestDto;
import com.shiva.RescueRide.dto.user.TowingRequestResponseDto;
import com.shiva.RescueRide.entities.TowingRequest;
import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.enums.AppEnums;
import com.shiva.RescueRide.exceptions.BadRequestException;
import com.shiva.RescueRide.exceptions.ResourceNotFoundException;
import com.shiva.RescueRide.repositories.TowingRequestRepository;
import com.shiva.RescueRide.repositories.UserRepository;
import com.shiva.RescueRide.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final TowingRequestRepository towingRequestRepo;

    // ------------------ CREATE REQUEST ------------------
    @Override
    public TowingRequestResponseDto createTowingRequest(String userEmail, CreateTowingRequestDto dto) {

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TowingRequest request = TowingRequest.builder()
                .user(user)
                .pickupLocation(dto.getPickupLocation())
                .dropLocation(dto.getDropLocation())
                .problemDescription(dto.getProblemDescription())
                .pickupLatitude(dto.getPickupLatitude())
                .pickupLongitude(dto.getPickupLongitude())
                .status(AppEnums.RequestStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .priceEstimate(0.0)  // Future enhancement
                .build();

        TowingRequest saved = towingRequestRepo.save(request);
        return mapToDto(saved);
    }

    // ------------------ GET MY REQUESTS ------------------
    @Override
    public List<TowingRequestResponseDto> getMyRequests(String userEmail) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return towingRequestRepo.findByUser(user)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    // ------------------ CANCEL REQUEST ------------------
    @Override
    public TowingRequestResponseDto cancelRequest(String userEmail, String requestId) {

        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TowingRequest request = towingRequestRepo.findById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));

        if (!request.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You are not allowed to cancel this request");
        }

        if (request.getStatus() == AppEnums.RequestStatus.COMPLETED
                || request.getStatus() == AppEnums.RequestStatus.CANCELLED) {
            throw new BadRequestException("Request cannot be cancelled");
        }

        request.setStatus(AppEnums.RequestStatus.CANCELLED);

        TowingRequest saved = towingRequestRepo.save(request);
        return mapToDto(saved);
    }

    // ------------------ MAPPING HELPER ------------------
    private TowingRequestResponseDto mapToDto(TowingRequest r) {
        return TowingRequestResponseDto.builder()
                .id(r.getId())
                .pickupLocation(r.getPickupLocation())
                .dropLocation(r.getDropLocation())
                .problemDescription(r.getProblemDescription())
                .status(r.getStatus())
                .pickupLatitude(r.getPickupLatitude())
                .pickupLongitude(r.getPickupLongitude())
                .requestedAt(r.getRequestedAt())
                .assignedAt(r.getAssignedAt())
                .completedAt(r.getCompletedAt())
                .priceEstimate(r.getPriceEstimate())
                .finalPrice(r.getFinalPrice())
                .assignedDriverId(
                        r.getAssignedDriver() != null ? r.getAssignedDriver().getId() : null
                )
                .assignedVehicleId(
                        r.getAssignedVehicle() != null ? r.getAssignedVehicle().getId() : null
                )
                .build();
    }
}
