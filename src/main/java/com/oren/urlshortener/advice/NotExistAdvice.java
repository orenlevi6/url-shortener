package com.oren.urlshortener.advice;

import com.oren.urlshortener.exceptions.NotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotExistAdvice {
    @ExceptionHandler(value = {NotExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleNotExistException(Exception err) {
        return new ErrorDetails("Not Exist Error", err.getMessage());
    }

}
