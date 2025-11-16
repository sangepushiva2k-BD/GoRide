package com.shiva.RescueRide.repositories;

import com.shiva.RescueRide.entities.TowingRequest;
import com.shiva.RescueRide.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TowingRequestRepository extends MongoRepository<TowingRequest, String> {
    List<TowingRequest> findByUser(User user);
}

