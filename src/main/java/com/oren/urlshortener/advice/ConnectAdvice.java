package com.oren.urlshortener.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class ConnectAdvice {
    @ExceptionHandler(value = {ConnectException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleConnectException(Exception err) {
        return new ErrorDetails("Connection Error", "Please make sure database is connected");
    }

}
