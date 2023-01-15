package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.projectbirdnest.model.DroneCapture;
import fi.projectbirdnest.model.Violation;
import fi.projectbirdnest.model.ViolationReport;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.List;


@Service
@RequiredArgsConstructor
class ViolationTaskScheduler {
    private final DroneService droneService;
    private final ViolationService violationService;

    private final Sinks.Many<ViolationReport> violationSink;

    @Scheduled(fixedRate = 2000)
    public void fetchAndProcessDroneCapture() throws JsonProcessingException {
        DroneCapture droneCapture = this.droneService.fetchDroneCapture();
        violationService.processDroneCapture(droneCapture);
        List<Violation> recentViolations = this.violationService.getRecentViolations();

        violationSink.tryEmitNext(new ViolationReport(droneCapture.getTimestamp(), recentViolations));
    }

    @Scheduled(fixedRate = 10000)
    public void deleteViolationsOlderThanTenMinutes(){
        violationService.deleteOldViolations();
    }
}
