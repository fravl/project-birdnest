package fi.projectbirdnest.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Component
public class ViolationController {

    private final Sinks.Many<String> violationSink;

    public Mono<ServerResponse> getViolationStream(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_NDJSON)
                .body(violationSink.asFlux(), String.class).log();
    }

}
