package fi.projectbirdnest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class ViolationReport {
    private Instant droneCaptureTimestamp;
    private List<Violation> violations;
}
