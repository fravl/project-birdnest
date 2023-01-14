package fi.projectbirdnest.service;

import fi.projectbirdnest.model.Pilot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DroneApi {

    private final RestTemplate restTemplate;
    private final String baseUri = "http://assignments.reaktor.com/birdnest";

    public ResponseEntity<String> getDrones() {
        final String uri = baseUri+"/drones";
        return this.restTemplate.getForEntity(uri, String.class);
    }

    public ResponseEntity<Pilot> getPilotInformation(String droneSerialNumber){
        final String uri = baseUri+"/pilots/"+droneSerialNumber;
        return this.restTemplate.getForEntity(uri, Pilot.class);
    }
}
