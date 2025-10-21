package com.vilas.hungerHub.exception;

import com.vilas.hungerHub.controllers.RestaurantController;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice(assignableTypes = {RestaurantController.class})
public class RestaurantExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ExceptionMessage> restaurantNotFound(RestaurantNotFoundException e){
        ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> userNotFound(UserNotFoundException e){
        ExceptionMessage error = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionMessage> handleValidationException(ConstraintViolationException e) {
        // Build a single string with all violations
        StringBuilder sb = new StringBuilder();
        e.getConstraintViolations().forEach(violation -> {
            sb.append(violation.getPropertyPath()).append(" : ")
                    .append(violation.getMessage()).append("; ");
        });

        ExceptionMessage error = new ExceptionMessage(
                HttpStatus.BAD_REQUEST.value(),
                sb.toString(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ExceptionMessage> handleTransactionSystemException(TransactionSystemException e) {
        Throwable cause = e.getRootCause();
        if (cause instanceof ConstraintViolationException cve) {
            // reuse your validation handler
            StringBuilder sb = new StringBuilder();
            cve.getConstraintViolations().forEach(violation -> {
                sb.append(violation.getPropertyPath()).append(": ")
                        .append(violation.getMessage()).append("; ");
            });

            ExceptionMessage error = new ExceptionMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    sb.toString(),
                    LocalDateTime.now()
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        // fallback to generic exception handler
        ExceptionMessage error = new ExceptionMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> allException(Exception e){
        ExceptionMessage error = new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now());
        log.error("exception", e);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
