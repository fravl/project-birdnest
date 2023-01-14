package fi.projectbirdnest.model;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class DroneCapture {
    private Instant timestamp;
    private List<Drone> droneList;
}
