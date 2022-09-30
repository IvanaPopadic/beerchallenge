package com.ivpo.beerchallenge.controller;

import com.ivpo.beerchallenge.exception.ResponseStatus;
import com.ivpo.beerchallenge.model.dto.BeerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/beerchallenge/api/v1")
public interface BeerRestControllerApi {

    @PostMapping(value = "/beers/init", produces = {"application/json"})
    public ResponseEntity<List<BeerDto>> init();

    @GetMapping(value = "/beers", produces = {"application/json"})
    public ResponseEntity<List<BeerDto>> beers();

    @GetMapping(value = "/beers/{id}", produces = {"application/json"})
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("id") String id);

    @DeleteMapping(value = "/beers/delete/{id}", produces = {"application/json"})
    public ResponseEntity<ResponseStatus> deleteBeerById(@PathVariable("id") String id);

}
