package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import pl.dziadosz.fundsmicroservice.domain.exception.IncorrectWithdrawalAmountException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

public class FundraiserCashService {

    public Fundraiser makeWithdrawal(Fundraiser fundraiser, FundraiserWithdrawal fundraiserWithdrawal) {
        BigDecimal amountToWithdraw = fundraiserWithdrawal.balance();
        BigDecimal newAmount = fundraiser
                .getBalance()
                .subtract(amountToWithdraw);
        if (newAmount.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IncorrectWithdrawalAmountException("Not enough cash to withdraw " + amountToWithdraw);
        }
        return new Fundraiser(fundraiser.getId(), fundraiser.getAccountId(), fundraiser.getName(), newAmount);
    }
}
