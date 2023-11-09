package com.ecommerce.bankservice.exception;


import com.ecommerce.bankservice.dto.FailureResponseHandler;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FailureResponseHandler handleInvalidArgument(MethodArgumentNotValidException ex) {
        return FailureResponseHandler
                .builder()
                .error(ex.getFieldError().getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(AuthenticationException.class)
    public FailureResponseHandler handleInvalidArgument(AuthenticationException ex) {
        return FailureResponseHandler.builder()
                .error("Authentication Filed")
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public FailureResponseHandler handleInvalidArgument(Exception ex) {
        return FailureResponseHandler.builder()
                .error(ex.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
    }

}