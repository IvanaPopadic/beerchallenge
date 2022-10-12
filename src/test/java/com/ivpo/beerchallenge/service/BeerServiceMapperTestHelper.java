package com.ivpo.beerchallenge.service;

import com.ivpo.beerchallenge.model.BeerEntity;
import com.ivpo.beerchallenge.model.BeerTemperatureEntity;
import com.ivpo.beerchallenge.model.TemperatureUnit;
import com.ivpo.beerchallenge.service.json.BeerJson;
import com.ivpo.beerchallenge.service.json.MashTempJson;
import com.ivpo.beerchallenge.service.json.MethodJson;
import com.ivpo.beerchallenge.service.json.TempJson;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class BeerServiceMapperTestHelper {

    public static void setUpBeerEntity(BeerEntity beer, Long id, String name, String description, List<BeerTemperatureEntity> beerTemperatures) {
        beer.setId(id);
        beer.setName(name);
        beer.setDescription(description);
        beer.setTemperature(getAvgTemperatureValue(beerTemperatures.get(0).getTempValue(), beerTemperatures.get(1).getTempValue(), beerTemperatures.get(2).getTempValue()));
        beer.setBeerTemperatureEntities(beerTemperatures);
    }

    public static BeerTemperatureEntity createBeerTemperatureEntity(Long id, Double tempValue, TemperatureUnit tempUnit, BeerEntity beer) {
        BeerTemperatureEntity temperature=new BeerTemperatureEntity();
        temperature.setId(id);
        temperature.setTempValue(tempValue);
        temperature.setTempUnit(tempUnit);
        temperature.setBeer(beer);

        return temperature;
    }

    public static BeerJson createBeerJson(Long id, String name, String description, MethodJson methodJson) {
        BeerJson beer = new BeerJson();
        beer.setId(id);
        beer.setName(name);
        beer.setDescription(description);
        beer.setMethodJson(methodJson);

        return beer;
    }

    public static TempJson createTempJson(Double tempValue, TemperatureUnit tempUnit) {
        TempJson tempJson = new TempJson();
        tempJson.setValue(tempValue);
        tempJson.setUnit(tempUnit.toString());

        return tempJson;
    }

    public static MethodJson crateMethodJson(List<MashTempJson> mashTempJsonList) {
        MethodJson methodJson = new MethodJson();
        methodJson.setMashTempsJson(mashTempJsonList);

        return methodJson;
    }

    public static MashTempJson crateMashTempJson(TempJson tempJson) {
        MashTempJson mashTempJson = new MashTempJson();
        mashTempJson.setTemp(tempJson);

        return mashTempJson;
    }

    public static Double getAvgTemperatureValue(Double tempValue1, Double tempValue2, Double tempValue3) {
        DoubleStream stream = DoubleStream.of(tempValue1, tempValue2, tempValue3);
        OptionalDouble obj = stream.average();
        Double avgValue = obj.getAsDouble();

        return avgValue;
    }
}
