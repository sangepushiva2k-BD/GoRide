package com.shiva.RescueRide.enums;

public class AppEnums {

    public enum UserRole {
        USER,
        DRIVER,
        ADMIN
    }

    public enum DriverStatus {
        AVAILABLE,
        BUSY,
        INACTIVE
    }

    public enum VehicleStatus {
        ACTIVE,
        IN_SERVICE,
        INACTIVE
    }

    public enum RequestStatus {
        REQUESTED,
        ASSIGNED,
        ON_THE_WAY,
        COMPLETED,
        CANCELLED
    }
}
