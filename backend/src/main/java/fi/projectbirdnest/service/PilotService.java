package fi.projectbirdnest.service;

import fi.projectbirdnest.model.Pilot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PilotService {

    private final DroneApi droneApi;

    public Pilot getPilotInformation(String droneSerialNumber){
        ResponseEntity<Pilot> apiResponse = this.droneApi.getPilotInformation(droneSerialNumber);
        if(apiResponse.getStatusCode().is2xxSuccessful()){
            return apiResponse.getBody();
        }
        return null;
    }


}
