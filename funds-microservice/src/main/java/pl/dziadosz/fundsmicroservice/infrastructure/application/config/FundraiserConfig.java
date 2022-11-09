package pl.dziadosz.fundsmicroservice.infrastructure.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserMapper;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserDepositAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserInformationAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter.FundraiserWithdrawalAdapter;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserDepositPort;
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
    public FundraiserWithdrawalPort fundraiserWithdrawalPort(FundraiserWebPort fundraiserWebPort, FundraiserRepositoryPort fundraiserRepositoryPort) {
        return new FundraiserWithdrawalAdapter(new FundraiserCashService(),
                new FundraiserWebService(fundraiserWebPort),
                new FundraiserSaveService(fundraiserRepositoryPort),
                new FundraiserSearchService(fundraiserRepositoryPort),
                new FundraiserMapper());
    }

    @Bean
    public FundraiserInformationPort fundraiserInformationPort(FundraiserRepositoryPort fundraiserRepositoryPort) {
        return new FundraiserInformationAdapter(new FundraiserInformationService(fundraiserRepositoryPort));
    }

    @Bean
    public FundraiserDepositPort fundraiserDepositPort(FundraiserRepositoryPort fundraiserRepositoryPort) {
        return new FundraiserDepositAdapter(new FundraiserCashService(),
                new FundraiserSaveService(fundraiserRepositoryPort),
                new FundraiserSearchService(fundraiserRepositoryPort),
                new FundraiserMapper());
    }
}
