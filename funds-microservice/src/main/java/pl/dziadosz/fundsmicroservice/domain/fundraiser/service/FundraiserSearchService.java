package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.exception.FundraiserNotFoundException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;

@RequiredArgsConstructor
public class FundraiserSearchService {
    private final FundraiserRepositoryPort repositoryPort;

    public Fundraiser findFundraiserForWithdrawal(FundraiserWithdrawal withdrawal) {
        return repositoryPort.findFundraiserWithAccountId(withdrawal.fundraiserId(), withdrawal.accountId())
                .map(fund -> new Fundraiser(fund.getId(), fund.getAccountId(), fund.getName(), fund.getBalance()))
                .orElseThrow(() -> new FundraiserNotFoundException(String.format("Fundraise %s not found", withdrawal.accountId())));
    }
}
