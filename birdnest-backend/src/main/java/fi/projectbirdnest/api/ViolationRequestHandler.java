package fi.projectbirdnest.api;


import fi.projectbirdnest.api.dto.ViolationReportDto;
import fi.projectbirdnest.model.ViolationReport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(violationSink.asFlux().map(ViolationReportDto::new), ViolationReportDto.class).log();
    }

}
