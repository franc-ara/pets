package com.domainchallenge.pets.utils.exception;

import com.domainchallenge.pets.controller.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PetExceptionHandler {

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFound(PetNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDto(exception.getTransactionId(), HttpStatus.NOT_FOUND.value(),
                        exception.getMessage(),
                        LocalDateTime.now()));
    }
}
