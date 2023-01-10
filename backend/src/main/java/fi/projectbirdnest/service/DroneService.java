package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fi.projectbirdnest.model.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DroneService {

    private final RestTemplate restTemplate;

    public List<Drone> getDrones() throws JsonProcessingException {
        String apiResponse = getDroneSnapshot();
        return extractDroneList(apiResponse);
    }

    private String getDroneSnapshot() {
        String uri = "http://assignments.reaktor.com/birdnest/drones";
        ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }

    private List<Drone> extractDroneList(String droneReport) throws JsonProcessingException {
        XmlMapper objectMapper = new XmlMapper();
        JsonNode body = objectMapper.readTree(droneReport);
        JsonNode drones = body.get("capture").get("drone");
        return Arrays.asList(objectMapper.treeToValue(drones, Drone[].class));
    }

}
