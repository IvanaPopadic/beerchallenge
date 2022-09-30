package com.ivpo.beerchallenge.repository;

import com.ivpo.beerchallenge.model.BeerEntity;

import java.util.List;

public interface BeerRepositoryApi {

    List<BeerEntity> getAllBeers();

    BeerEntity getBeerById(Long beerId);

    List<BeerEntity> saveAll(List<BeerEntity> beerEntities);

    void deleteById(final Long beerId);

    long count();
}
