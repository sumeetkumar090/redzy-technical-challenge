package com.rezdy.lunch.exception;

import com.rezdy.lunch.service.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class LunchExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(DateTimeParseException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
