package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundraiserWebServiceTest {
    @InjectMocks
    FundraiserWebService webService;
    @Mock
    FundraiserWebPort webPort;

    @Test
    void shouldReturnEventWhenWithdrawalProceed() {
        FundraiserWithdrawal withdrawal = givenWithdrawal();
        when(webPort.makeWithdrawCall(any())).then(
                invocation -> new FundraiseWithdrawalResponse(withdrawal.fundraiserId(), withdrawal.accountId(), withdrawal.amount())
        );

        FundraiserEvent fundraiserEvent = webService.create(withdrawal);
        Assertions.assertNotNull(fundraiserEvent);
        Assertions.assertEquals(withdrawal.fundraiserId(), fundraiserEvent.getFundraiserId());
        Assertions.assertEquals(withdrawal.accountId(), fundraiserEvent.getAccountId());
    }

    private FundraiserWithdrawal givenWithdrawal() {
        return new FundraiserWithdrawal(1L, 1L, "Test", BigDecimal.valueOf(5));
    }
}