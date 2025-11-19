package com.shiva.RescueRide.dto.user;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TowingRequestResponseDto {

    private String id;
    private String pickupLocation;
    private String dropLocation;
    private String problemDescription;
    private AppEnums.RequestStatus status;

    private LocalDateTime requestedAt;
    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;

    private Double pickupLatitude;
    private Double pickupLongitude;

    private Double priceEstimate;
    private Double finalPrice;

    private String assignedDriverId;
    private UUID assignedVehicleId;
}
