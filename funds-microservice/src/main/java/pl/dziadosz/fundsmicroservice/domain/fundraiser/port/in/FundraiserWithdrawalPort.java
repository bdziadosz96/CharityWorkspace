package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;

public interface FundraiserWithdrawalPort {
    FundraiserEvent withdraw(FundraiserWithdrawal fundraiserWithdrawal);
}
