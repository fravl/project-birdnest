package fi.projectbirdnest;

import fi.projectbirdnest.api.ViolationController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Sinks;

@Configuration
public class ProjectConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RouterFunction<ServerResponse> routes(ViolationController handler) {
        return RouterFunctions.route(RequestPredicates.GET("/stream"), handler::getViolationStream);
    }

    @Bean
    public Sinks.Many<String> violationSink(){
        return Sinks.many().replay().latest();
    }

}
