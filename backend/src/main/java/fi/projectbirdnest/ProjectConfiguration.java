package fi.projectbirdnest;

import fi.projectbirdnest.model.ViolationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Sinks;

@Configuration
public class ProjectConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sinks.Many<ViolationReport> violationSink(){
        return Sinks.many().replay().latest();
    }

}
