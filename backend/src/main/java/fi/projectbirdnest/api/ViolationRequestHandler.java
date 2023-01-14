package fi.projectbirdnest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fi.projectbirdnest.api.dto.ViolationReportDto;
import fi.projectbirdnest.model.ViolationReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Component
public class ViolationRequestHandler {

    private final Sinks.Many<ViolationReport> violationSink;

    public Mono<ServerResponse> getViolationStream(ServerRequest serverRequest) {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON)
                .body(violationSink.asFlux().map(ViolationReportDto::new), ViolationReportDto.class).log();
    }

}
