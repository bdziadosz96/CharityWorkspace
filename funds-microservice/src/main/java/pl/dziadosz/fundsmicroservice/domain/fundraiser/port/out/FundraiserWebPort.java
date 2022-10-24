package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

public interface FundraiserWebPort {
    FundraiseWithdrawalResponse makeWithdrawCall(FundraiserWithdrawal fundraiserWithdrawal);
}
