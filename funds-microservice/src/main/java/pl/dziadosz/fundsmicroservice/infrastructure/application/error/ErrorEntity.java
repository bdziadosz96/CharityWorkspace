package pl.dziadosz.fundsmicroservice.infrastructure.application.error;

import java.time.LocalDateTime;

record ErrorEntity(
        String message,
        LocalDateTime thrownAt){
    ErrorEntity(String message){
        this(message, LocalDateTime.now());
    }
}