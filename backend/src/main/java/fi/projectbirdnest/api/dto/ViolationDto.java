package fi.projectbirdnest.api.dto;


import fi.projectbirdnest.model.Violation;
import lombok.Getter;
import lombok.Setter;


import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ViolationDto {
    private Long id;
    private String lastSeen;
    private double closestDistanceToNest;
    private PilotDto pilot;

    public ViolationDto(Violation violation){
        this.id = violation.getId();
        this.closestDistanceToNest = ((double) Math.round(violation.getClosestDistanceToNest()/10))/100;
        this.lastSeen = ZonedDateTime.ofInstant(violation.getLastSeen(), ZoneOffset.UTC).toString();
        this.pilot = new PilotDto(violation.getPilot());
    }
}
