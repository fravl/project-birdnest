package fi.projectbirdnest.service;

import fi.projectbirdnest.model.Drone;
import fi.projectbirdnest.model.Pilot;
import fi.projectbirdnest.model.Violation;
import fi.projectbirdnest.persistence.ViolationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViolationService {

    private final PilotService pilotService;
    private final ViolationRepository violationRepository;

    private final Sinks.Many<String> violationSink;


    private Violation saveViolation(Violation violation){
        return violationRepository.save(violation);
    }

    private Optional<Violation> getByDroneSerialNumber(final String droneSerialNumber){
        return violationRepository.findByDrone_SerialNumber(droneSerialNumber);
    }

    public Optional<Violation> processDrone(final Drone drone){
        final double distanceToNest = getDistanceToNest(drone);
        if(isInNoDroneZone(distanceToNest)){
            Optional<Violation> _violation = getByDroneSerialNumber(drone.getSerialNumber());
            if(_violation.isPresent()){
                Violation violation = _violation.get();
                violation.setLastSeen(Instant.now());
                violation.setClosestDistanceToNest(Math.min(violation.getClosestDistanceToNest(), distanceToNest));
                violation.getDrone().setPositionX(drone.getPositionX());
                violation.getDrone().setPositionY(drone.getPositionY());

                return Optional.of(saveViolation(violation));
            }
            Violation violation = new Violation();
            Optional<Pilot> pilot = pilotService.getPilotInformation(drone.getSerialNumber());
            violation.setPilot(pilot.orElse(null));
            violation.setDrone(drone);
            violation.setClosestDistanceToNest(distanceToNest);
            violation.setLastSeen(Instant.now());
            return Optional.of(saveViolation(violation));
        }
        return Optional.empty();
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
