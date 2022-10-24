package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;

@RequiredArgsConstructor
public class FundraiserWebService {
    private final FundraiserWebPort fundraiserWebPort;

    public FundraiserEvent create(final FundraiserWithdrawal fundraiserWithdrawal) {
        FundraiseWithdrawalResponse response = fundraiserWebPort.makeWithdrawCall(fundraiserWithdrawal);
        return FundraiserEvent.fromWithdrawal(response.fundraiseId(), response.accountId(), response.amount());
    }
}
