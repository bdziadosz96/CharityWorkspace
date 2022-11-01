package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.config;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import pl.dziadosz.fundsmicroservice.domain.exception.ExternalWithdrawalProcessException;
import pl.dziadosz.fundsmicroservice.domain.exception.InternalWithdrawalProcessException;

@Component
public class FundraiserRestErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException {
        return (
                response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(final ClientHttpResponse response) throws IOException {
        System.out.println(response.getStatusCode());
        if (response.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ExternalWithdrawalProcessException(response.getStatusText());
        } else if (response.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new InternalWithdrawalProcessException(response.getStatusText());
        }
    }
}
