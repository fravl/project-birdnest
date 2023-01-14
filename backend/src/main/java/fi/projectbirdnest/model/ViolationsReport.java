package fi.projectbirdnest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class ViolationsReport {
    private Instant timestamp;
    private List<Violation> violations;
}
