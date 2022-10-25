package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import pl.dziadosz.fundsmicroservice.domain.exception.IncorrectWithdrawalAmountException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

public class FundraiserCashService {

    public Fundraiser makeWithdrawal(Fundraiser fundraiser, FundraiserWithdrawal fundraiserWithdrawal) {
        BigDecimal amountToWithdraw = fundraiserWithdrawal.amount();
        BigDecimal newAmount = fundraiser
                .getBalance()
                .subtract(amountToWithdraw);
        if (newAmount.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IncorrectWithdrawalAmountException("Not enough cash to withdraw " + amountToWithdraw);
        }
        return new Fundraiser(fundraiser.getId(), fundraiser.getAccountId(), fundraiser.getName(), newAmount);
    }

    public FundraiserModel makeDeposit(final FundraiserModel fundraiser, final FundraiserDeposit fundraiserDeposit) {
        BigDecimal amountToDeposit = fundraiserDeposit.amount();
        BigDecimal newAmount = fundraiser
                .balance()
                .add(amountToDeposit);
        return new FundraiserModel(fundraiser.id(), fundraiser.accountId(), fundraiser.name(), newAmount);
    }
}
