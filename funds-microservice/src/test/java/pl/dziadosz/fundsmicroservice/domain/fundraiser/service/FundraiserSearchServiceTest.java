package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.exception.FundraiserNotFoundException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundraiserSearchServiceTest {
    @InjectMocks
    FundraiserSearchService searchService;
    @Mock
    FundraiserRepositoryPort repositoryPort;

    @Test
    void shouldReturnFundraiserForWithdrawal() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserWithdrawal withdrawal = givenWithdrawal();
        when(repositoryPort.findFundraiserWithAccountId(fundraiser.getId(), fundraiser.getAccountId()))
                .thenReturn(Optional.of(fundraiser));

        Fundraiser result = searchService.findFundraiserForWithdrawal(withdrawal);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowExceptionWhenWithdrawalFundraiserNotFound() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserWithdrawal withdrawal = givenWithdrawal();
        when(repositoryPort.findFundraiserWithAccountId(fundraiser.getId(), fundraiser.getAccountId()))
                .thenThrow(FundraiserNotFoundException.class);

        Assertions.assertThrows(FundraiserNotFoundException.class, () -> searchService.findFundraiserForWithdrawal(withdrawal));
    }

    @Test
    void shouldReturnFundraiserForDeposit() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserDeposit deposit = givenDeposit();
        when(repositoryPort.findFundraiserById(fundraiser.getId()))
                .thenReturn(Optional.of(givenFundraiserModel()));

        Fundraiser result = searchService.findFundraiserForDeposit(deposit);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowExceptionWhenDepositFundraiserNotFound() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserDeposit deposit = givenDeposit();
        when(repositoryPort.findFundraiserById(fundraiser.getId()))
                .thenThrow(FundraiserNotFoundException.class);

        Assertions.assertThrows(FundraiserNotFoundException.class, () -> searchService.findFundraiserForDeposit(deposit));
    }

    private FundraiserWithdrawal givenWithdrawal() {
        return new FundraiserWithdrawal(1L, 1L, "Test", BigDecimal.valueOf(5));
    }

    private FundraiserDeposit givenDeposit() {
        return new FundraiserDeposit(1L, BigDecimal.TEN);
    }

    private Fundraiser givenFundraiser() {
        return new Fundraiser(1L, 1L, "Test", BigDecimal.TEN);
    }

    private FundraiserModel givenFundraiserModel() {
        return new FundraiserModel(1L, 1L, "Test", BigDecimal.TEN);
    }
}