package com.ivpo.beerchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException() {}

    public BeerNotFoundException(String message) {
        super(message);
    }

    public BeerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
