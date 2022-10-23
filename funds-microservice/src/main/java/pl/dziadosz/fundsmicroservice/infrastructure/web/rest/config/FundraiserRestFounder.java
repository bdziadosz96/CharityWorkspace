package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FundraiserRestFounder {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new FundraiserRestErrorHandler())
                .build();
    }
}
