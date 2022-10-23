package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;

@RequiredArgsConstructor
public class FundraiserWebService {
    private final FundraiserWebPort fundraiserWebPort;

    public FundraiserEvent create(final FundraiserWithdrawal fundraiserWithdrawal) {
        FundraiseWithdrawalResponse response = fundraiserWebPort.makeWithdrawCall(fundraiserWithdrawal);
        return FundraiserEvent.fromWithdrawal(response.fundraiseId(), response.accountId(), response.amount());
    }
}
