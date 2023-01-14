package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fi.projectbirdnest.model.DroneCapture;
import fi.projectbirdnest.model.ViolationsReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;


@Service
@RequiredArgsConstructor
class DroneReportProcessor {
    private final DroneService droneService;
    private final ViolationService violationService;

    private final Sinks.Many<String> violationSink;

    @Scheduled(fixedRate = 2000)
    public void processDroneReport() throws JsonProcessingException {
        DroneCapture droneCapture = this.droneService.fetchDrones();
        violationService.processDroneCapture(droneCapture);
        ViolationsReport violationsReport = this.violationService.getViolationsReport();

        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        violationSink.tryEmitNext(objectMapper.writeValueAsString(violationsReport));
    }

    @Scheduled(fixedRate = 10000)
    public void deleteViolationsOlderThanTenMinutes(){
        violationService.deleteOldViolations();
    }
}
