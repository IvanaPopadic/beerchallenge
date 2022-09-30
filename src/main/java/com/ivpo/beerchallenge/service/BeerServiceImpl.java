package com.ivpo.beerchallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivpo.beerchallenge.model.BeerEntity;
import com.ivpo.beerchallenge.model.dto.BeerDto;
import com.ivpo.beerchallenge.repository.BeerRepositoryApi;
import com.ivpo.beerchallenge.service.json.BeerJson;
import com.ivpo.beerchallenge.service.mapping.BeerServiceMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeerServiceImpl implements BeerServiceApi {
    public static final String RANDOM_BEER_URL = "https://api.punkapi.com/v2/beers/random";
    private BeerRepositoryApi beerRepository;

    private BeerServiceMapper beerServiceMapper;

    public BeerServiceImpl(BeerRepositoryApi beerRepository, BeerServiceMapper beerServiceMapper) {
        this.beerRepository = beerRepository;
        this.beerServiceMapper = beerServiceMapper;
    }

    @Override
    public List<BeerDto> initBeers() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BeerEntity> randomBeers = new ArrayList<>();
        long count = beerRepository.count();
        while (count < 10) {
            try {
                BeerJson[] beers = objectMapper.readValue(new URL(RANDOM_BEER_URL), BeerJson[].class);
                BeerEntity beerEntity = beerServiceMapper.mapBeerJsonToBeerEntity(beers[0]);
                randomBeers.add(beerEntity);
                count++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<BeerEntity> savedBeers = beerRepository.saveAll(randomBeers);
        return beerServiceMapper.mapBeerEntitiesToBeerDtos(savedBeers);
    }

    @Override
    public List<BeerDto> getAllBeers() {
        List<BeerEntity> beers = beerRepository.getAllBeers();
        return beerServiceMapper.mapBeerEntitiesToBeerDtos(beers);
    }

    @Override
    public BeerDto getBeerById(Long beerId) {
        BeerEntity beerEntity = beerRepository.getBeerById(beerId);
        return beerServiceMapper.mapBeerEntityToBeerDto(beerEntity);
    }

    @Override
    public void deleteById(Long beerId) {
        beerRepository.deleteById(beerId);
    }
}
