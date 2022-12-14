package com.ivpo.beerchallenge.service;

import com.ivpo.beerchallenge.dto.BeerDto;

import java.util.List;

public interface BeerServiceApi {

    public List<BeerDto> initBeers();

    List<BeerDto> getAllBeers();

    BeerDto getBeerById(Long beerId);

    void deleteById(final Long beerId);
}
