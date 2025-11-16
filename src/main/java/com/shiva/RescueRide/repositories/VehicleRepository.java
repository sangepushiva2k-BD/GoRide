package com.shiva.RescueRide.repositories;

import com.shiva.RescueRide.entities.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
