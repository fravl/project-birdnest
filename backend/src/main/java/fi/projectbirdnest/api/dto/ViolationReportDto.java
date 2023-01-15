package fi.projectbirdnest.api.dto;

import fi.projectbirdnest.model.ViolationReport;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ViolationReportDto {
    private String droneCaptureTimestamp;
    private List<ViolationDto> violations;

    public ViolationReportDto(ViolationReport violationReport){
        this.droneCaptureTimestamp = ZonedDateTime.ofInstant(violationReport.getDroneCaptureTimestamp(), ZoneOffset.UTC).toString();
        this.violations = violationReport.getViolations().stream().map(ViolationDto::new).collect(Collectors.toList());
    }
}
