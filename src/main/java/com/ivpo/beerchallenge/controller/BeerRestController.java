package com.ivpo.beerchallenge.controller;

import com.ivpo.beerchallenge.dto.BeerDto;
import com.ivpo.beerchallenge.exception.BeerNotFoundException;
import com.ivpo.beerchallenge.exception.ResponseMessage;
import com.ivpo.beerchallenge.exception.ResponseStatus;
import com.ivpo.beerchallenge.service.BeerServiceImpl;
import com.ivpo.beerchallenge.util.LogEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@RestController
public class BeerRestController implements BeerRestControllerApi {

    private BeerServiceImpl beerService;

    private MessageSource messageSource;

    public BeerRestController(BeerServiceImpl beerService, MessageSource messageSource) {
        this.beerService = beerService;
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<List<BeerDto>> init() {
        List<BeerDto> savedBeers = beerService.initBeers();

        return new ResponseEntity<>(savedBeers, HttpStatus.OK);
    }

    @Override
    @LogEvent
    public ResponseEntity<List<BeerDto>> beers() {
        List<BeerDto> allBeers = beerService.getAllBeers();

        return new ResponseEntity<>(allBeers, HttpStatus.OK);
    }

    @Override
    @LogEvent
    public ResponseEntity<BeerDto> getBeerById(String id) {
        BeerDto beer = beerService.getBeerById(Long.parseLong(id));

        return new ResponseEntity<>(beer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStatus> deleteBeerById(String id) {
        beerService.deleteById(Long.parseLong(id));

        final String responseMessage = messageSource.getMessage("BEER_IS_DELETED", new String[]{id}, LocaleContextHolder.getLocale());
        return createSuccessfulResponse(HttpStatus.OK, responseMessage);
    }

    private ResponseEntity<ResponseStatus> createSuccessfulResponse(final HttpStatus httpStatus, final String message) {
        final ResponseMessage responseMessage = new ResponseMessage(message);
        final ResponseStatus responseStatus = new ResponseStatus(true, Collections.singletonList(responseMessage), OffsetDateTime.now());
        return new ResponseEntity<>(responseStatus, httpStatus);
    }
}
