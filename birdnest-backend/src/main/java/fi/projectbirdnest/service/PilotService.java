package fi.projectbirdnest.service;

import fi.projectbirdnest.model.Pilot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PilotService {

    private final DroneApi droneApi;

    public Optional<Pilot> getPilotInformation(String droneSerialNumber){
        ResponseEntity<Pilot> apiResponse = this.droneApi.getPilotInformation(droneSerialNumber);
        return Optional.ofNullable(apiResponse.getBody());
    }


}
