package com.shiva.RescueRide.controllers;

import com.shiva.RescueRide.entities.Vehicle;
import com.shiva.RescueRide.services.interfaces.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/driver")

public class DriverController {

    @Autowired
    private DriverService driverService;

      @PutMapping("update-vehicle")
        public Vehicle updateVehicle(@RequestParam String id, @RequestBody Vehicle vehicle){
        return driverService.updateUserVehicle(id,vehicle);
    }

    @GetMapping("get-all/vehicles")
    public List<Vehicle> getAllVehicles(Authentication auth){
        return driverService.getAllVehicles();
    }
    @DeleteMapping("delete-all/vehicles")
    public String deleteAllVehicles(Authentication auth){
        return driverService.deleteAllVehicles();
    }
}
