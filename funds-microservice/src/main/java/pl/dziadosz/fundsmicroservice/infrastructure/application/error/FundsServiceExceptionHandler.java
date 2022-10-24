package pl.dziadosz.fundsmicroservice.infrastructure.application.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dziadosz.fundsmicroservice.domain.exception.ExternalWithdrawalProcessException;
import pl.dziadosz.fundsmicroservice.domain.exception.FundraiserNotFoundException;
import pl.dziadosz.fundsmicroservice.domain.exception.IncorrectWithdrawalAmountException;
import pl.dziadosz.fundsmicroservice.domain.exception.InternalWithdrawalProcessException;

@RestControllerAdvice
public class FundsServiceExceptionHandler {

    @ExceptionHandler(InternalWithdrawalProcessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleInterruptedWithdrawalProcess(InternalWithdrawalProcessException exception) {
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(ExternalWithdrawalProcessException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorEntity handleExternalWithdrawalProcessException(ExternalWithdrawalProcessException exception) {
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(FundraiserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleFundraiserNotFoundException(FundraiserNotFoundException exception) {
        return new ErrorEntity(exception.getMessage());
    }

    @ExceptionHandler(IncorrectWithdrawalAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleIncorrectWithdrawalAmountException(IncorrectWithdrawalAmountException exception) {
        return new ErrorEntity(exception.getMessage());
    }
}


