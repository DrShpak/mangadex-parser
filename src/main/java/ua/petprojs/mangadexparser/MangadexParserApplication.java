package ua.petprojs.mangadexparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ua.petprojs.mangadexparser.customizer.ProxyCustomizer;

@SpringBootApplication()
public class MangadexParserApplication {
	public static void main(String[] args) {
		SpringApplication.run(MangadexParserApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.additionalCustomizers(new ProxyCustomizer()).build();
	}
}
