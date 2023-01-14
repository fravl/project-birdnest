package fi.projectbirdnest.api.dto;


import fi.projectbirdnest.model.Violation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ViolationDto {
    private Long id;
    private Instant lastSeen;
    private double closestDistanceToNest;
    private PilotDto pilot;

    public ViolationDto(Violation violation){
        this.id = violation.getId();
        this.closestDistanceToNest = ((double) Math.round(violation.getClosestDistanceToNest()/10))/100;
        this.lastSeen = violation.getLastSeen();
        this.pilot = new PilotDto(violation.getPilot());
    }
}
