package com.oren.urlshortener.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import redis.clients.jedis.exceptions.InvalidURIException;

@RestControllerAdvice
public class InvalidURLAdvice {
    @ExceptionHandler(value = {InvalidURIException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleInvalidURIException(Exception err) {
        return new ErrorDetails("Invalid URL Error", err.getMessage());
    }

}
