package com.shiva.RescueRide.entities;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {

    @Id
    private String id;

    private String name;

    private String phone;

    private String licenseNumber;

    private AppEnums.DriverStatus status;

    private Double currentLatitude;
    private Double currentLongitude;
}
