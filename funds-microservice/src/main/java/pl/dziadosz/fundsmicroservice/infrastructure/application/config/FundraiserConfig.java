package pl.dziadosz.fundsmicroservice.infrastructure.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserInformationAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserWithdrawalAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserInformationPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserInformationService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserWebService;

@Configuration
public class FundraiserConfig {

    @Bean
    public FundraiserWithdrawalPort fundraiserWithdrawalPort(
            FundraiserWebPort fundraiserWebPort,
            FundraiserRepositoryPort fundraiserRepositoryPort) {
        return new FundraiserWithdrawalAdapter(
                new FundraiserCashService(),
                new FundraiserWebService(fundraiserWebPort),
                new FundraiserSaveService(fundraiserRepositoryPort),
                new FundraiserSearchService(fundraiserRepositoryPort));
    }

    @Bean
    public FundraiserInformationPort fundraiserInformationPort(
            FundraiserRepositoryPort fundraiserRepositoryPort
    ) {
        return new FundraiserInformationAdapter(new FundraiserInformationService(fundraiserRepositoryPort));
    }
}
