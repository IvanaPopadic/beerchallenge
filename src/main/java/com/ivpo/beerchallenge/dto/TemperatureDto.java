package com.ivpo.beerchallenge.dto;

import com.ivpo.beerchallenge.model.TemperatureUnit;

public class TemperatureDto {

    private Double tempValue;

    private TemperatureUnit tempUnit;

    public Double getTempValue() {
        return tempValue;
    }

    public void setTempValue(Double tempValue) {
        this.tempValue = tempValue;
    }

    public TemperatureUnit getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(TemperatureUnit tempUnit) {
        this.tempUnit = tempUnit;
    }
}
