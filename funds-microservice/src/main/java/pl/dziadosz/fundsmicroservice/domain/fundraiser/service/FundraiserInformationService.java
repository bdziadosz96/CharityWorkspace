package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;

public class FundraiserInformationService {
    private final FundraiserRepositoryPort repositoryPort;

    public FundraiserInformationService(final FundraiserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public FundraiserInformationModel findInformation(Long fundraiserId) {
        BigDecimal withdrawalSummary = repositoryPort.findWithdrawalSummary(fundraiserId);
        BigDecimal depositSummary = repositoryPort.findDepositSummary(fundraiserId);
        return new FundraiserInformationModel(depositSummary,withdrawalSummary);
    }
}
