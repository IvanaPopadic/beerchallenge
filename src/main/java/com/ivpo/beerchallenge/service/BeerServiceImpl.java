package com.ivpo.beerchallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivpo.beerchallenge.exception.BeerNotFoundException;
import com.ivpo.beerchallenge.model.BeerEntity;
import com.ivpo.beerchallenge.dto.BeerDto;
import com.ivpo.beerchallenge.repository.BeerRepositoryApi;
import com.ivpo.beerchallenge.service.json.BeerJson;
import com.ivpo.beerchallenge.service.mapping.BeerServiceMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeerServiceImpl implements BeerServiceApi {
    public static final String RANDOM_BEER_URL = "https://api.punkapi.com/v2/beers/random";
    private BeerRepositoryApi beerRepository;

    private MessageSource messageSource;

    private BeerServiceMapper beerServiceMapper;

    public BeerServiceImpl(BeerRepositoryApi beerRepository, MessageSource messageSource, BeerServiceMapper beerServiceMapper) {
        this.beerRepository = beerRepository;
        this.messageSource = messageSource;
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
        if (beerEntity == null) {
            final String errorMessage = getMessage("BEER_NOT_FOUND", new Long[]{beerId});
            throw new BeerNotFoundException(errorMessage);
        }

        return beerServiceMapper.mapBeerEntityToBeerDto(beerEntity);
    }

    @Override
    public void deleteById(Long beerId) {
        try {
            beerRepository.deleteById(beerId);
        } catch (Exception e) {
            final String responseMessage = getMessage("BEER_NOT_EXIST", new Long[]{beerId});
            throw new BeerNotFoundException(responseMessage, e);
        }
    }

    private String getMessage(String code, @Nullable Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
