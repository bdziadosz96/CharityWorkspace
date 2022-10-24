package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;
import pl.dziadosz.fundsmicroservice.domain.exception.InternalWithdrawalProcessException;

@RequiredArgsConstructor
public class FundraiserWebAdapter implements FundraiserWebPort {
    private final RestTemplate restTemplate;
    public static final String URI = "http://localhost:8081/fundraise";

    @Override
    public FundraiseWithdrawalResponse makeWithdrawCall(final FundraiserWithdrawal fundraiserWithdrawal) {

        RequestEntity<FundraiserWithdrawal> request = constructRequest(fundraiserWithdrawal);
        ResponseEntity<FundraiseWithdrawalResponse> response;
        try {
            response = restTemplate
                    .exchange(request, FundraiseWithdrawalResponse.class);
        } catch (ResourceAccessException e) {
            throw new InternalWithdrawalProcessException(e.getMessage());
        }
        return response.getBody();
    }

    private RequestEntity<FundraiserWithdrawal> constructRequest(final FundraiserWithdrawal fundraiserWithdrawal) {
        return RequestEntity
                .post(FundraiserWebAdapter.URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(fundraiserWithdrawal);
    }
}
