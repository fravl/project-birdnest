package fi.projectbirdnest.service;

import fi.projectbirdnest.model.Drone;
import fi.projectbirdnest.model.Violation;
import fi.projectbirdnest.persistence.ViolationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViolationService {

    private final ViolationRepository violationRepository;

    private Violation createViolation(Violation violation){
        return violationRepository.save(violation);
    }

    public boolean droneIsInNoDroneZone(Drone drone){
        double NEST_X = 250000;
        double NEST_Y = 250000;
        double distanceDroneToNest = calculateDistance(
                drone.getPositionX(),
                drone.getPositionY(),
                NEST_X,
                NEST_Y
        );
        double NDZ_RADIUS = 100;
        return distanceDroneToNest < NDZ_RADIUS;
    }

    /**
     * Calculates the distance between two points on earth in meters.
     * Uses Haversine formula, see https://en.wikipedia.org/wiki/Haversine_formula
     */
    private double calculateDistance(double lat1, double long1,
                                            double lat2, double long2) {

        double latDistance = Math.toRadians(lat1 - lat2);
        double lngDistance = Math.toRadians(long1 - long2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double EARTH_RADIUS = 6371000;
        return EARTH_RADIUS * c;
    }
}
