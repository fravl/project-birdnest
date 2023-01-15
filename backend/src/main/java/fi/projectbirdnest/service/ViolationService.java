package fi.projectbirdnest.service;

import fi.projectbirdnest.model.*;
import fi.projectbirdnest.persistence.ViolationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ViolationService {

    private final PilotService pilotService;
    private final ViolationRepository violationRepository;

    public List<Violation> getRecentViolations(){
        Instant tenMinutesAgo = Instant.now().minus(10, ChronoUnit.MINUTES);
        return this.violationRepository.findByLastSeenGreaterThan(tenMinutesAgo);
    }

    public void deleteOldViolations(){
        Instant tenMinutesAgo = Instant.now().minus(10, ChronoUnit.MINUTES);
        violationRepository.findByLastSeenLessThan(tenMinutesAgo);
        violationRepository.deleteAll(violationRepository.findByLastSeenLessThan(tenMinutesAgo));
    }

    public void processDroneCapture(final DroneCapture droneCapture){
        for (Drone drone: droneCapture.getDroneList()) {
            final double distanceToNest = getDistanceToNest(drone);

            Optional<Violation> _violation = getByDroneSerialNumber(drone.getSerialNumber());
            Violation violation;

            if(_violation.isPresent()){
                // Update Violation
                violation = _violation.get();
                violation.setLastSeen(droneCapture.getTimestamp());
                violation.setClosestDistanceToNest(Math.min(violation.getClosestDistanceToNest(), distanceToNest));
                violation.getDrone().setPositionX(drone.getPositionX());
                violation.getDrone().setPositionY(drone.getPositionY());
                saveViolation(violation);
            } else if(isInNoDroneZone(distanceToNest)){
                // Create new Violation
                violation = new Violation();
                Optional<Pilot> pilot = pilotService.getPilotInformation(drone.getSerialNumber());
                violation.setPilot(pilot.orElse(null));
                violation.setDrone(drone);
                violation.setClosestDistanceToNest(distanceToNest);
                violation.setLastSeen(droneCapture.getTimestamp());
                saveViolation(violation);
            }
        }
    }

    private void saveViolation(Violation violation){
        violationRepository.save(violation);
    }

    private Optional<Violation> getByDroneSerialNumber(final String droneSerialNumber){
        return violationRepository.findByDrone_SerialNumber(droneSerialNumber);
    }
    
    private boolean isInNoDroneZone(final double distanceToNest){
        double NDZ_RADIUS = 100000;
        return distanceToNest < NDZ_RADIUS;
    }

    private double getDistanceToNest(final Drone drone) {
        final double NEST_X = 250000;
        final double NEST_Y = 250000;
        final double xDifference = NEST_X-drone.getPositionX();
        final double yDifference = NEST_Y- drone.getPositionY();

        return Math.sqrt(Math.pow(xDifference,2)+Math.pow(yDifference,2));
    }
}
