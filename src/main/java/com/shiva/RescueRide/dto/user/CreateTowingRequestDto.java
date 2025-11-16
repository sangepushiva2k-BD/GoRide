package com.shiva.RescueRide.dto.user;

import lombok.Data;

@Data
public class CreateTowingRequestDto {

    private String pickupLocation;
    private String dropLocation;
    private String problemDescription;

    private Double pickupLatitude;
    private Double pickupLongitude;
}
