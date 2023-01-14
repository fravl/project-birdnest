package fi.projectbirdnest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fi.projectbirdnest.model.Drone;
import fi.projectbirdnest.model.DroneCapture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
class DroneService {

    private final DroneApi droneApi;

    public DroneCapture fetchDrones() throws JsonProcessingException {
        String apiResponseBody = droneApi.getDrones().getBody();
        return XmlResponseToDroneCapture(apiResponseBody);
    }

    private DroneCapture XmlResponseToDroneCapture(String droneReport) throws JsonProcessingException {
        XmlMapper objectMapper = Jackson2ObjectMapperBuilder.xml().build();
        JsonNode body = objectMapper.readTree(droneReport);

        Instant timestamp = Instant.parse(body.get("capture").get("snapshotTimestamp").asText());

        JsonNode dronesJson = body.get("capture").get("drone");
        List<Drone> droneList = Arrays.asList(objectMapper.treeToValue(dronesJson, Drone[].class));

        return new DroneCapture(timestamp,droneList);
    }

}
