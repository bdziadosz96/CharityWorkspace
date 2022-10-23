package pl.dziadosz.fundsmicroservice.infrastructure.application.exception;

public class InterruptedWithdrawalProcessException extends RuntimeException{
    public InterruptedWithdrawalProcessException(final String message) {
        super(message);
    }
}
