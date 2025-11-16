package com.shiva.RescueRide.repositories;

import com.shiva.RescueRide.entities.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, String> {
}
