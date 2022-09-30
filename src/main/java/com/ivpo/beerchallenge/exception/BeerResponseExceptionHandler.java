package com.ivpo.beerchallenge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Collections;

@ControllerAdvice
public class BeerResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BeerResponseExceptionHandler.class);

    @ExceptionHandler({BeerNotFoundException.class})
    public ResponseEntity<Object> handleBeerNotFoundException(final BeerNotFoundException ex) {
        logException(ex);
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Object> createResponse(final HttpStatus httpStatus, final String message) {
        final ResponseMessage responseMessage = new ResponseMessage(message);
        final ResponseStatus responseStatus = new ResponseStatus(false, Collections.singletonList(responseMessage), OffsetDateTime.now());
        return new ResponseEntity<>(responseStatus, httpStatus);
    }

    private void logException(final Exception e) {
        LOG.error("[{}] {}: {}", getClass().getName(), e.getClass().getName(), e.getMessage(), e);
    }
}
