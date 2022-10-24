package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

public interface FundraiserWithdrawalPort {
    FundraiserEventModel withdraw(FundraiserWithdrawal fundraiserWithdrawal);
}
