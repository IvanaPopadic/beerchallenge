package com.ivpo.beerchallenge.repository;

import com.ivpo.beerchallenge.model.BeerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepositoryApi {

    private final BeerJpaRepository beerJpaRepository;

    @Autowired
    public BeerRepositoryImpl(BeerJpaRepository beerJpaRepository) {
        this.beerJpaRepository = beerJpaRepository;
    }

    @Override
    public List<BeerEntity> getAllBeers() {
        return beerJpaRepository.findAll();
    }

    @Override
    public BeerEntity getBeerById(Long beerId) {
        return beerJpaRepository.findById(beerId).orElse(null);
    }

    @Override
    public List<BeerEntity> saveAll(List<BeerEntity> beerEntities) {
        return beerJpaRepository.saveAll(beerEntities);
    }

    @Override
    public void deleteById(Long beerId) {
        beerJpaRepository.deleteById(beerId);
    }

    @Override
    public long count() {
        return beerJpaRepository.count();
    }
}
