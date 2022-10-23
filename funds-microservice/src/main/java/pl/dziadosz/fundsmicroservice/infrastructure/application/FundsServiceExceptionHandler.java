package pl.dziadosz.fundsmicroservice.infrastructure.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.dziadosz.fundsmicroservice.infrastructure.application.exception.InterruptedWithdrawalProcessException;

@RestControllerAdvice
public class FundsServiceExceptionHandler {

    @ExceptionHandler(value = InterruptedWithdrawalProcessException.class)
    public ResponseEntity<Object> handleInterruptedWithdrawalProcess(InterruptedWithdrawalProcessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
