package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;

@Configuration
@RequiredArgsConstructor
public class FundraiserWebConfig {
    private final RestTemplate restTemplate;

    @Bean
    public FundraiserWebPort fundraiseWebPort() {
        return new FundraiserWebAdapter(restTemplate);
    }
}
