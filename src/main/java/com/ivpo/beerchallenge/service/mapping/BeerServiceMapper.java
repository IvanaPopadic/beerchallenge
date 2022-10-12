package com.ivpo.beerchallenge.service.mapping;

import com.ivpo.beerchallenge.model.BeerEntity;
import com.ivpo.beerchallenge.model.BeerTemperatureEntity;
import com.ivpo.beerchallenge.model.TemperatureUnit;
import com.ivpo.beerchallenge.dto.BeerDto;
import com.ivpo.beerchallenge.dto.TemperatureDto;
import com.ivpo.beerchallenge.service.json.BeerJson;
import com.ivpo.beerchallenge.service.json.MashTempJson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Component
public class BeerServiceMapper {

    public BeerEntity mapBeerJsonToBeerEntity(BeerJson beerJson) {
        BeerEntity beerEntity = new BeerEntity();
        beerEntity.setId(beerJson.getId());
        beerEntity.setName(beerJson.getName());
        beerEntity.setDescription(beerJson.getDescription());
        beerEntity.setTemperature(getAverageTemperature(beerJson));
        setBeerTemperatureEntities(beerJson, beerEntity);

        return beerEntity;
    }

    private static void setBeerTemperatureEntities(BeerJson beerJson, BeerEntity beerEntity) {
        List<BeerTemperatureEntity> beerTemperatureEntities = new ArrayList<>();
        List<MashTempJson> mashTemps = beerJson.getMethodJson().getMashTempsJson();
        for (MashTempJson mt : mashTemps) {
            BeerTemperatureEntity beerTemperatureEntity = new BeerTemperatureEntity();
            beerTemperatureEntity.setBeer(beerEntity);
            beerTemperatureEntity.setTempValue(mt.getTemp().getValue());
            beerTemperatureEntity.setTempUnit(TemperatureUnit.fromValue(mt.getTemp().getUnit()));
            beerTemperatureEntities.add(beerTemperatureEntity);
        }
        beerEntity.setBeerTemperatureEntities(beerTemperatureEntities);
    }

    public BeerDto mapBeerEntityToBeerDto(BeerEntity beerEntity) {
        if (beerEntity == null) {
            return null;
        }
        BeerDto beerDto = new BeerDto();
        beerDto.setId(beerEntity.getId());
        beerDto.setName(beerEntity.getName());
        beerDto.setDescription(beerEntity.getDescription());
        beerDto.setMeanTemp(beerEntity.getTemperature());
        beerDto.setTemperatures(mapBeerTemperatures(beerEntity.getBeerTemperatureEntities()));

        return beerDto;
    }

    private static List<TemperatureDto> mapBeerTemperatures(List<BeerTemperatureEntity> beerTemperatureEntities) {
        List<TemperatureDto> temperatures = new ArrayList<>();
        for (BeerTemperatureEntity beerTemp : beerTemperatureEntities) {
            TemperatureDto tempDto = new TemperatureDto();
            tempDto.setTempValue(beerTemp.getTempValue());
            tempDto.setTempUnit(beerTemp.getTempUnit());
            temperatures.add(tempDto);
        }
        return temperatures;
    }

    public List<BeerDto> mapBeerEntitiesToBeerDtos(List<BeerEntity> beerEntityList) {
        List<BeerDto> beerDtoList = new ArrayList<>();
        for (BeerEntity beerEntity : beerEntityList) {
            BeerDto beerDto = mapBeerEntityToBeerDto(beerEntity);
            beerDtoList.add(beerDto);
        }
        return beerDtoList;
    }

    private static double getAverageTemperature(BeerJson beerJson) {
        List<MashTempJson> mashTemps = beerJson.getMethodJson().getMashTempsJson();
        DoubleSummaryStatistics summaryStatistics = mashTemps.stream()
                .mapToDouble((mt) -> mt.getTemp().getValue()).summaryStatistics();

        return summaryStatistics.getAverage();
    }
}
