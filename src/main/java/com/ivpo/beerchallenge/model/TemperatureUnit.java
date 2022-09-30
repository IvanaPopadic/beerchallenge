package com.ivpo.beerchallenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TemperatureUnit {
    CELSIUS("celsius"), FAHRENHEIT("fahrenheit");

    private String value;

    TemperatureUnit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static TemperatureUnit fromValue(String text) {
        if (null == text) {
            return null;
        }
        for (TemperatureUnit tu : TemperatureUnit.values()) {
            if (String.valueOf(tu.value).equals(text)) {
                return tu;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "', allowed values are celsius and fahrenheit.");
    }
}
