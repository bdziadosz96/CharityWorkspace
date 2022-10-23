package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;

public interface FundraiserWebPort {
    FundraiseWithdrawalResponse makeWithdrawCall(FundraiserWithdrawal fundraiserWithdrawal);
}
