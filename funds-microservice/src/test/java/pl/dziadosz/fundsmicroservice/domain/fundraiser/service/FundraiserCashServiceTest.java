package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.exception.IncorrectWithdrawalAmountException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

@ExtendWith(MockitoExtension.class)
class FundraiserCashServiceTest {
    @InjectMocks
    FundraiserCashService cashService;

    @Test
    void shouldIncreaseFundraiserTotalBalanceWithDeposit() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserDeposit deposit = givenDeposit();

        Fundraiser result = cashService.makeDeposit(fundraiser, deposit);

        Assertions.assertEquals(BigDecimal.valueOf(20), result.getBalance());
        Assertions.assertNotNull(result);
    }

    @Test
    void shouldDecreaseFundraiserTotalBalanceWithWithdrawal() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserWithdrawal deposit = givenWithdrawal();

        Fundraiser result = cashService.makeWithdrawal(fundraiser, deposit);

        Assertions.assertEquals(BigDecimal.valueOf(5), result.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenAmountToWithdrawIsTooHigh() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserWithdrawal withdrawal = givenTooHighWithdrawal();

        Assertions.assertThrows(IncorrectWithdrawalAmountException.class,
                () -> cashService.makeWithdrawal(fundraiser, withdrawal));
    }


    private FundraiserWithdrawal givenWithdrawal() {
        return new FundraiserWithdrawal(1L, 1L, "Test", BigDecimal.valueOf(5));
    }

    private FundraiserWithdrawal givenTooHighWithdrawal() {
        return new FundraiserWithdrawal(1L, 1L, "Test", BigDecimal.valueOf(15));
    }

    private FundraiserDeposit givenDeposit() {
        return new FundraiserDeposit(1L,BigDecimal.TEN);
    }

    private Fundraiser givenFundraiser() {
        return new Fundraiser(1L, 1L, "Test", BigDecimal.TEN);
    }
}