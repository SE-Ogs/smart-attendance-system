package com.attendance.attendance.ipaddress;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Set;

@Service
public class LocationService {
    
    // iAcademy Cebu coordinates (5th Floor Filinvest Cebu Cyberzone Tower 2)
    private static final double IACADEMY_LATITUDE = 10.3157;
    private static final double IACADEMY_LONGITUDE = 123.9066;
    
    // Allowed radius in meters (e.g., 50 meters for building proximity)
    private static final double ALLOWED_RADIUS_METERS = 50.0;
    
    // Campus IP ranges (as backup validation)
    private final Set<String> CAMPUS_IPS = Set.of(
        "10.0.7"
    );
    
    public boolean isWithinCampusProximity(double latitude, double longitude, String ipAddress) {
        // Primary validation: GPS coordinates
        if (isValidGPSLocation(latitude, longitude)) {
            return true;
        }
        
        // Fallback validation: IP address (for when GPS is unavailable)
        if (isValidCampusIP(ipAddress)) {
            return true;
        }
        
        return false;
    }
    
    private boolean isValidGPSLocation(double latitude, double longitude) {
        double distance = calculateDistance(
            IACADEMY_LATITUDE, IACADEMY_LONGITUDE,
            latitude, longitude
        );
        return distance <= ALLOWED_RADIUS_METERS;
    }
    
    private boolean isValidCampusIP(String ip) {
        return CAMPUS_IPS.stream().anyMatch(ip::startsWith);
    }
    
    // Haversine formula to calculate distance between two GPS coordinates
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double EARTH_RADIUS_KM = 6371.0;
        
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLatRad = Math.toRadians(lat2 - lat1);
        double deltaLonRad = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS_KM * c * 1000; // Convert to meters
    }
    
    public Map<String, Object> getLocationInfo() {
        return Map.of(
            "campusName", "iAcademy Cebu",
            "address", "5th Floor Filinvest Cebu Cyberzone Tower 2, Salinas Drive, corner W Geonzon St, Cebu City",
            "latitude", IACADEMY_LATITUDE,
            "longitude", IACADEMY_LONGITUDE,
            "allowedRadius", ALLOWED_RADIUS_METERS + " meters"
        );
    }
}