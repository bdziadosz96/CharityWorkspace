package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserWebPort;

@RequiredArgsConstructor
public class FundraiserWebAdapter implements FundraiserWebPort {
    private final RestTemplate restTemplate;
    public static final String URI = "http://localhost:8081/fundraise";

    @Override
    public FundraiseWithdrawalResponse makeWithdrawCall(final FundraiserWithdrawal fundraiserWithdrawal) {

        RequestEntity<FundraiserWithdrawal> request = constructRequest(fundraiserWithdrawal, URI);
        ResponseEntity<FundraiseWithdrawalResponse> response = restTemplate
                .exchange(request, FundraiseWithdrawalResponse.class);
        return response.getBody();
    }


    private static RequestEntity<FundraiserWithdrawal> constructRequest(final FundraiserWithdrawal fundraiserWithdrawal, final String URI) {
        return RequestEntity
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .body(fundraiserWithdrawal);
    }
}
