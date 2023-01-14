package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.projectbirdnest.model.Drone;
import fi.projectbirdnest.model.Violation;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneReportProcessor {
    private final PilotService pilotService;
    private final DroneService droneService;
    private final ViolationService violationService;

    @Scheduled(fixedRate = 2000)
    public void processDroneReport() throws JsonProcessingException {
        List<Drone> droneList = this.droneService.fetchDrones();
        for (Drone drone: droneList) {
            this.violationService.processDrone(drone);
        }
    }
}
