package pl.dziadosz.fundsmicroservice.domain.exception;

public class FundraiserNotFoundException extends RuntimeException{
    public FundraiserNotFoundException(final String message) {
        super(message);
    }
}
