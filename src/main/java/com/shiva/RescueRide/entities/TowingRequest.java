package com.shiva.RescueRide.entities;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "towing_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TowingRequest {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Driver assignedDriver;

    @DBRef
    private Vehicle assignedVehicle;

    private String pickupLocation;
    private String dropLocation;
    private String problemDescription;

    private Double pickupLatitude;
    private Double pickupLongitude;

    private AppEnums.RequestStatus status;

    private LocalDateTime requestedAt;
    private LocalDateTime assignedAt;
    private LocalDateTime completedAt;

    private Double priceEstimate;
    private Double finalPrice;
}
