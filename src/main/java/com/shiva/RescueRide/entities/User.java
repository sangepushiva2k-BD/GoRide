package com.shiva.RescueRide.entities;

import com.shiva.RescueRide.enums.AppEnums;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    private String id;     // Mongo ObjectId as String

    private String name;

    private String email;

    private String phone;

    private String password;

    private AppEnums.UserRole role; // USER / DRIVER / ADMIN
}
