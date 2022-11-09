package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import java.math.BigDecimal;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.exception.FundraiserNotFoundException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserMapper;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FundraiserDepositAdapterTest {
    private final FundraiserRepositoryPort repositoryPort = Mockito.mock(FundraiserRepositoryPort.class);
    private final FundraiserCashService cashService = Mockito.mock(FundraiserCashService.class, Mockito.CALLS_REAL_METHODS);
    private final FundraiserSaveService saveService = new FundraiserSaveService(repositoryPort);
    private final FundraiserSearchService searchService = new FundraiserSearchService(repositoryPort);
    private final FundraiserMapper fundraiserMapper = new FundraiserMapper();
    private final FundraiserDepositAdapter depositAdapter = new FundraiserDepositAdapter(
            cashService,
            saveService,
            searchService,
            fundraiserMapper);


    @Test
    void shouldThrowExceptionWhenFundraiserIsNotFound() {
        FundraiserDeposit request = depositRequest();

        Assertions.assertThrows(FundraiserNotFoundException.class, () -> depositAdapter.deposit(request));
    }

    @Test
    void shouldReturnDepositEventWithProperValues() {
        FundraiserDeposit request = depositRequest();

        Optional<FundraiserModel> fundraiserModel = Optional.of(new FundraiserModel(1L, 1L, "test", BigDecimal.TEN));
        Mockito.when(repositoryPort.findFundraiserById(request.fundraiserId()))
                        .thenReturn(fundraiserModel);
        Mockito.when(repositoryPort.save(any(Fundraiser.class))).thenReturn(
                new Fundraiser(1L,1L,"test",BigDecimal.valueOf(11)));
        Mockito.when(repositoryPort.save(any(FundraiserEvent.class))).thenReturn(
                new FundraiserEvent("12abc",1L, 1L, BigDecimal.ONE,FundraiserEventType.DEPOSIT));


        FundraiserEventModel deposit = depositAdapter.deposit(request);
        Assertions.assertEquals(BigDecimal.ONE, deposit.amount());
        Assertions.assertEquals("12abc", deposit.uuid());
        Assertions.assertEquals(1L, deposit.fundraiserId());
        Assertions.assertEquals(FundraiserEventType.DEPOSIT, deposit.eventType());
        Assertions.assertEquals(1L, deposit.accountId());

        Mockito.verify(cashService, Mockito.times(1)).makeDeposit(any(Fundraiser.class), any());
    }

    @NotNull
    private FundraiserDeposit depositRequest() {
        return new FundraiserDeposit(1L, BigDecimal.ONE);
    }
}