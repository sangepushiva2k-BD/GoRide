package com.shiva.RescueRide.services.interfaces;

import com.shiva.RescueRide.entities.User;
import com.shiva.RescueRide.entities.Vehicle;
import com.shiva.RescueRide.exceptions.BadRequestException;
import com.shiva.RescueRide.exceptions.ResourceNotFoundException;
import com.shiva.RescueRide.repositories.DriverRepository;
import com.shiva.RescueRide.repositories.UserRepository;
import com.shiva.RescueRide.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepo;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleRepository vehicleRepo;


    public Vehicle updateUserVehicle(String id, Vehicle newVehicleData) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Vehicle existingVehicle = user.getVehicle();

        // If user has no vehicle yet → create one
        if (existingVehicle == null) {
            existingVehicle = new Vehicle();
            existingVehicle.setId(UUID.randomUUID());   // Use String if your Vehicle ID is string
        }

        // Validate fields
        if (newVehicleData.getRegistrationNumber() != null && newVehicleData.getRegistrationNumber().isBlank()) {
            throw new BadRequestException("Vehicle registration number is mandatory");
        }

        if (newVehicleData.getType() != null && newVehicleData.getType().isBlank()) {
            throw new BadRequestException("Vehicle type is mandatory");
        }
        existingVehicle.setColor(newVehicleData.getColor());
        existingVehicle.setCapacity(newVehicleData.getCapacity());
        existingVehicle.setModel(newVehicleData.getModel());
        existingVehicle.setType(newVehicleData.getType());
        existingVehicle.setStatus(newVehicleData.getStatus());
        existingVehicle.setRegistrationNumber(newVehicleData.getRegistrationNumber());

        // Save updated vehicle (does NOT create new one)
        vehicleRepo.save(existingVehicle);

        // Assign back to user
        user.setVehicle(existingVehicle);
        userRepo.save(user);

        return existingVehicle;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepo.findAll();
    }

    public String deleteAllVehicles(){
       List<User> users = userRepo.findAll();
       for(User item : users){
           if(item.getVehicle()!=null){
               item.setVehicle(null);
           }
       }
        userRepo.saveAll(users);
         vehicleRepo.deleteAll();
        return "Deleted Successfully";
    }
}
