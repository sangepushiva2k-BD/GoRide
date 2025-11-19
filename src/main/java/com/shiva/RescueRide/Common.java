package com.shiva.RescueRide;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class Common {

    public static String getCurrentUserEmail(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return principal.toString();
    }
}
