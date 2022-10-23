package pl.dziadosz.fundsmicroservice.infrastructure.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserServiceAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserWithdrawalAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserServicePort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserPrivilegeService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserWebService;

@Configuration
public class FundraiserConfig {

    @Bean
    public FundraiserServicePort fundraiserServicePort(FundraiserRepositoryPort fundraiserRepositoryPort) {
        return new FundraiserServiceAdapter(new FundraiserService(fundraiserRepositoryPort));
    }

    @Bean
    public FundraiserWithdrawalPort fundraiserWithdrawalPort(
            FundraiserWebPort fundraiserWebPort,
            FundraiserRepositoryPort fundraiserRepositoryPort) {
        FundraiserService fundraiserService = new FundraiserService(fundraiserRepositoryPort);
        return new FundraiserWithdrawalAdapter(
                new FundraiserWebService(fundraiserWebPort),
                fundraiserService,
                new FundraiserPrivilegeService(fundraiserService));
    }
}
