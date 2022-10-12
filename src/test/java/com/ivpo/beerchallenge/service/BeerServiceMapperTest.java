package com.ivpo.beerchallenge.service;

import com.ivpo.beerchallenge.model.BeerEntity;
import com.ivpo.beerchallenge.model.BeerTemperatureEntity;
import com.ivpo.beerchallenge.model.TemperatureUnit;
import com.ivpo.beerchallenge.dto.BeerDto;
import com.ivpo.beerchallenge.service.json.BeerJson;
import com.ivpo.beerchallenge.service.json.MashTempJson;
import com.ivpo.beerchallenge.service.json.MethodJson;
import com.ivpo.beerchallenge.service.json.TempJson;
import com.ivpo.beerchallenge.service.mapping.BeerServiceMapper;
import liquibase.repackaged.org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static com.ivpo.beerchallenge.service.BeerServiceMapperTestHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
public class BeerServiceMapperTest {

    public static final Long BEER_ID = RandomUtils.nextLong(100000L, 199999L);
    private static final String BEER_NAME = "Movember";
    private static final String BEER_DESCRIPTION = "A deliciously robust, black malted beer with a decadent dark, dry cocoa flavour.";

    public static final Long BEER_TEMP_ID = RandomUtils.nextLong(100000L, 199999L);
    public static final Long BEER_TEMP_ID_1 = RandomUtils.nextLong(100000L, 199999L);
    public static final Long BEER_TEMP_ID_2 = RandomUtils.nextLong(100000L, 199999L);

    private static final Double TEMPERATURE_VALUE = RandomUtils.nextDouble(10.0, 150.0);
    private static final Double TEMPERATURE_VALUE_1 = RandomUtils.nextDouble(10.0, 150.0);
    private static final Double TEMPERATURE_VALUE_2 = RandomUtils.nextDouble(10.0, 150.0);

    private BeerJson beerJson;
    private MashTempJson mashTempJson;
    private MethodJson methodJson;
    private TempJson tempJson;
    private final BeerEntity beer = new BeerEntity();
    private BeerTemperatureEntity temperature, temperature1, temperature2;

    @Autowired
    private BeerServiceMapper underTest;


    @BeforeEach
    public void setUp() {
        tempJson = createTempJson(TEMPERATURE_VALUE, TemperatureUnit.CELSIUS);
        mashTempJson = crateMashTempJson(tempJson);
        methodJson = crateMethodJson(Arrays.asList(mashTempJson));

        temperature = createBeerTemperatureEntity(BEER_TEMP_ID, TEMPERATURE_VALUE, TemperatureUnit.CELSIUS, beer);
        temperature1 = createBeerTemperatureEntity(BEER_TEMP_ID_1, TEMPERATURE_VALUE_1, TemperatureUnit.CELSIUS, beer);
        temperature2 = createBeerTemperatureEntity(BEER_TEMP_ID_2, TEMPERATURE_VALUE_2, TemperatureUnit.CELSIUS, beer);

        setUpBeerEntity(beer, BEER_ID, BEER_NAME, BEER_DESCRIPTION, Arrays.asList(temperature, temperature1, temperature2));
    }

    @Test
    public void mapBeerJsonToBeerEntity() {
        // given
        beerJson = createBeerJson(BEER_ID, BEER_NAME, BEER_DESCRIPTION, methodJson);

        // when
        final BeerEntity result = underTest.mapBeerJsonToBeerEntity(beerJson);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(BEER_ID));
        assertThat(result.getName(), is(BEER_NAME));
        assertThat(result.getDescription(), is(BEER_DESCRIPTION));
        assertThat(result.getTemperature(), is(TEMPERATURE_VALUE));
        assertThat(result.getBeerTemperatureEntities(), is(notNullValue()));
        assertThat(result.getBeerTemperatureEntities().size(), is(1));
    }

    @Test
    public void mapBeerJsonToBeerEntity_withTemperatureList() {
        // given
        TempJson tempJson1 = createTempJson(TEMPERATURE_VALUE_1, TemperatureUnit.CELSIUS);
        MashTempJson mashTempJson1 = crateMashTempJson(tempJson1);

        TempJson tempJson2 = createTempJson(TEMPERATURE_VALUE_2, TemperatureUnit.CELSIUS);
        MashTempJson mashTempJson2 = crateMashTempJson(tempJson2);

        methodJson = crateMethodJson(Arrays.asList(mashTempJson, mashTempJson1, mashTempJson2));

        beerJson = createBeerJson(BEER_ID, BEER_NAME, BEER_DESCRIPTION, methodJson);

        // when
        final BeerEntity result = underTest.mapBeerJsonToBeerEntity(beerJson);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(BEER_ID));
        assertThat(result.getName(), is(BEER_NAME));
        assertThat(result.getDescription(), is(BEER_DESCRIPTION));
        assertThat(result.getTemperature(), is(getAvgTemperatureValue(TEMPERATURE_VALUE, TEMPERATURE_VALUE_1, TEMPERATURE_VALUE_2)));
        assertThat(result.getBeerTemperatureEntities(), is(notNullValue()));
        assertThat(result.getBeerTemperatureEntities().size(), is(3));
        assertThat(result.getBeerTemperatureEntities().get(0), is(samePropertyValuesAs(temperature, "id")));
        assertThat(result.getBeerTemperatureEntities().get(1), is(samePropertyValuesAs(temperature1, "id")));
        assertThat(result.getBeerTemperatureEntities().get(2), is(samePropertyValuesAs(temperature2, "id")));
    }

    @Test
    public void mapBeerEntityToBeerDto() {
        // when
        final BeerDto result = underTest.mapBeerEntityToBeerDto(beer);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getId(), is(BEER_ID));
        assertThat(result.getName(), is(BEER_NAME));
        assertThat(result.getDescription(), is(BEER_DESCRIPTION));
        assertThat(result.getMeanTemp(), is(getAvgTemperatureValue(TEMPERATURE_VALUE, TEMPERATURE_VALUE_1, TEMPERATURE_VALUE_2)));
        assertThat(result.getTemperatures(), is(notNullValue()));
        assertThat(result.getTemperatures().size(), is(3));
    }
}
