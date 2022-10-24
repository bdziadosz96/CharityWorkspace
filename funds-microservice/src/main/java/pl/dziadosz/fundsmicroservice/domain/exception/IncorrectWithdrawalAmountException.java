package pl.dziadosz.fundsmicroservice.domain.exception;

public class IncorrectWithdrawalAmountException extends RuntimeException{
    public IncorrectWithdrawalAmountException(final String message) {
        super(message);
    }
}
