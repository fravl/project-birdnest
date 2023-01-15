package fi.projectbirdnest.api.dto;

import fi.projectbirdnest.model.ViolationReport;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViolationReportDto {
    private Instant droneCaptureTimestamp;
    private List<ViolationDto> violations;

    public ViolationReportDto(ViolationReport violationReport){
        this.droneCaptureTimestamp = violationReport.getDroneCaptureTimestamp();
        this.violations = violationReport.getViolations().stream().map(ViolationDto::new).collect(Collectors.toList());
    }
}