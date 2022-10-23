package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

@Configuration
@RequiredArgsConstructor
public class FundraiserDatabaseConfig {

    @Bean
    public FundraiserRepositoryPort fundraiseRepositoryPort(FundraiserRepository fundraiserRepository,
                                                            FundraiserEventRepository eventRepository) {
        return new FundraiserDatabaseAdapter(fundraiserRepository, eventRepository);
    }
}
