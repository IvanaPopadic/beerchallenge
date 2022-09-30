package com.ivpo.beerchallenge.controller;

import com.ivpo.beerchallenge.exception.BeerNotFoundException;
import com.ivpo.beerchallenge.exception.ResponseMessage;
import com.ivpo.beerchallenge.exception.ResponseStatus;
import com.ivpo.beerchallenge.model.dto.BeerDto;
import com.ivpo.beerchallenge.service.BeerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@RestController
public class BeerRestController implements BeerRestControllerApi {

    BeerServiceImpl beerService;

    public BeerRestController(BeerServiceImpl beerService) {
        this.beerService = beerService;
    }

    @Override
    public ResponseEntity<List<BeerDto>> init() {
        List<BeerDto> savedBeers = beerService.initBeers();

        return new ResponseEntity<>(savedBeers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BeerDto>> beers() {
        List<BeerDto> allBeers = beerService.getAllBeers();

        return new ResponseEntity<>(allBeers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerDto> getBeerById(String id) {
        BeerDto beer = beerService.getBeerById(Long.parseLong(id));
        if (beer == null) {
            final String errorMessage = String.format("Beer with the id %s is not found.", id);
            throw new BeerNotFoundException(errorMessage);
        }

        return new ResponseEntity<>(beer, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStatus> deleteBeerById(String id) {
        try {
            beerService.deleteById(Long.parseLong(id));
        } catch (Exception e) {
            final String errorMessage = String.format("Beer with the id %s does not exist.", id);
            throw new BeerNotFoundException(errorMessage, e);
        }

        return createSuccessfulResponse(HttpStatus.OK, String.format("Beer with the id %s is deleted.", id));
    }

    private ResponseEntity<ResponseStatus> createSuccessfulResponse(final HttpStatus httpStatus, final String message) {
        final ResponseMessage responseMessage = new ResponseMessage(message);
        final ResponseStatus responseStatus = new ResponseStatus(true, Collections.singletonList(responseMessage), OffsetDateTime.now());
        return new ResponseEntity<>(responseStatus, httpStatus);
    }
}
