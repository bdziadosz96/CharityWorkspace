package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.dziadosz.fundsmicroservice.domain.exception.ExternalWithdrawalProcessException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

@RequiredArgsConstructor
public class FundraiserWebAdapter implements FundraiserWebPort {
    private final RestTemplate restTemplate;
    public static final String WITHDRAWAL_SERVICE_URI = "http://localhost:8081/fundraise";

    @Override
    public FundraiseWithdrawalResponse makeWithdrawCall(final FundraiserWithdrawal fundraiserWithdrawal) {

        RequestEntity<FundraiserWithdrawal> request = constructRequest(fundraiserWithdrawal);
        try {
            return restTemplate
                    .exchange(request, FundraiseWithdrawalResponse.class)
                    .getBody();
        } catch (ResourceAccessException e) {
            throw new ExternalWithdrawalProcessException("Resource access exception");
        }
    }

    private RequestEntity<FundraiserWithdrawal> constructRequest(final FundraiserWithdrawal fundraiserWithdrawal) {
        return RequestEntity
                .post(FundraiserWebAdapter.WITHDRAWAL_SERVICE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(fundraiserWithdrawal);
    }
}
