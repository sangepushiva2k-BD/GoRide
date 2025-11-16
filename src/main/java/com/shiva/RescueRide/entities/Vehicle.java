package com.shiva.RescueRide.entities;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Vehicle {

    @Id
    private String id;

    private String registrationNumber;

    private String type;   // CAR, BIKE, TRUCK, TOW_TRUCK
    private String model;
    private Integer capacity;

    private AppEnums.VehicleStatus status;

    @DBRef
    private Driver driver;   // optional
}
