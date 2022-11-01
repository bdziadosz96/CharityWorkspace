package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.infrastructure.web.rest.config.FundraiserRestErrorHandler;


class FundraiserWebAdapterTest {
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new FundraiserRestErrorHandler())
            .build();
    private FundraiserWebAdapter webAdapter = new FundraiserWebAdapter(restTemplate);


    @Test
    void shouldReturnResponseWithIdWhenSuccess() {
        FundraiserWithdrawal request = new FundraiserWithdrawal(1L, 1L, "test", BigDecimal.TEN);
        FundraiseWithdrawalResponse fundraiseWithdrawalResponse = webAdapter.makeWithdrawCall(request);

        Assertions.assertEquals(fundraiseWithdrawalResponse.fundraiseId(), 1L);
    }
}