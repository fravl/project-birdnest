package fi.projectbirdnest;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.projectbirdnest.service.DroneService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class ProjectBirdnestApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjectBirdnestApplication.class, args);
		DroneService droneService = new DroneService(new RestTemplate());
		droneService.getDrones();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
