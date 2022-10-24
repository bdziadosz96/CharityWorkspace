package pl.dziadosz.fundsmicroservice.domain.exception;

public class InternalWithdrawalProcessException extends RuntimeException{
    public InternalWithdrawalProcessException(final String message) {
        super(message);
    }
}
