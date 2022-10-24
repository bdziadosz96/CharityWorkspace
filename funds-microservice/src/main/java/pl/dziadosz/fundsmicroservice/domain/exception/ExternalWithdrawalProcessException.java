package pl.dziadosz.fundsmicroservice.domain.exception;

public class ExternalWithdrawalProcessException extends RuntimeException{
    public ExternalWithdrawalProcessException(final String message) {
        super(message);
    }
}
