package com.oren.urlshortener.advice;

import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MongoWriteAdvice {
    @ExceptionHandler(value = {MongoWriteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleMongoWriteException(Exception err) {
        return new ErrorDetails("Username Error", "Username is already taken");
    }

}
