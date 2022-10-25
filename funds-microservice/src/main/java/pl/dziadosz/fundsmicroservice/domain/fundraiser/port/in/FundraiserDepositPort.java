package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;

public interface FundraiserDepositPort {
    FundraiserEventModel deposit(FundraiserDeposit fundraiserDeposit);
}
