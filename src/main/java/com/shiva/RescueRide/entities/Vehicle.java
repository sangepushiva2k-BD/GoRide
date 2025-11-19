package com.shiva.RescueRide.entities;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {

    @Id
    private UUID id;

    private String registrationNumber;

    private String type;   // CAR, BIKE, TRUCK, TOW_TRUCK
    private String model;
    private String color;
    private Integer capacity;

    private AppEnums.VehicleStatus status;

}
