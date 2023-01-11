package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fi.projectbirdnest.model.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DroneService {

    private final DroneApi droneApi;

    public List<Drone> getDrones() throws JsonProcessingException {
        String apiResponseBody = droneApi.getDrones().getBody();
        return extractDroneList(apiResponseBody);
    }

    private List<Drone> extractDroneList(String droneReport) throws JsonProcessingException {
        XmlMapper objectMapper = new XmlMapper();
        JsonNode body = objectMapper.readTree(droneReport);
        JsonNode drones = body.get("capture").get("drone");
        return Arrays.asList(objectMapper.treeToValue(drones, Drone[].class));
    }

}
