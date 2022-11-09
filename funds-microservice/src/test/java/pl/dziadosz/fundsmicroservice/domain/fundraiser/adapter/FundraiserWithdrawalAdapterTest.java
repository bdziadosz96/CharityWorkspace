package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserMapper;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserWebService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class FundraiserWithdrawalAdapterTest {
    private final FundraiserRepositoryPort repositoryPort = Mockito.mock(FundraiserRepositoryPort.class, Mockito.CALLS_REAL_METHODS);
    private final FundraiserWebPort webPort = Mockito.mock(FundraiserWebPort.class);

    private final FundraiserCashService cashService = new FundraiserCashService();
    private final FundraiserWebService webService = new FundraiserWebService(webPort);
    private final FundraiserSearchService searchService = new FundraiserSearchService(repositoryPort);
    private final FundraiserMapper fundraiserMapper = Mockito.mock(FundraiserMapper.class);
    private final FundraiserSaveService saveService = new FundraiserSaveService(repositoryPort);

    private final FundraiserWithdrawalAdapter withdrawalAdapter =
            new FundraiserWithdrawalAdapter(cashService, webService, saveService, searchService, fundraiserMapper);

    @Test
    void shouldReturnDepositEventWithProperValues() {
        Mockito.when(repositoryPort.findFundraiserWithAccountId(1L, 1L))
                .thenReturn(Optional.of(new Fundraiser(1L, 1L, "asd", BigDecimal.TEN)));

        Mockito.when(webPort.makeWithdrawCall(any()))
                .thenReturn(new FundraiseWithdrawalResponse(1L,1L,BigDecimal.TEN));

        Mockito.when(fundraiserMapper.toModel(any()))
                .thenReturn(new FundraiserEventModel("12ab",1L, 1L, BigDecimal.ZERO, FundraiserEventType.WITHDRAWAL));

        FundraiserEventModel result = withdrawalAdapter.withdraw(withdrawalRequest());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("12ab", result.uuid());
        Assertions.assertEquals(1L, result.fundraiserId());
        Assertions.assertEquals(1L, result.accountId());
        Assertions.assertEquals(BigDecimal.ZERO, result.amount());
        Assertions.assertEquals(FundraiserEventType.WITHDRAWAL, result.eventType());
    }

    private FundraiserWithdrawal withdrawalRequest() {
        return new FundraiserWithdrawal(1L, 1L, "test", BigDecimal.TEN);
    }
}