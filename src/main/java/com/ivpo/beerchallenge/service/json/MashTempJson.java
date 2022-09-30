package com.ivpo.beerchallenge.service.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MashTempJson {
    @JsonProperty("temp")
    TempJson temp;

    public TempJson getTemp() {
        return temp;
    }

    public void setTemp(TempJson temp) {
        this.temp = temp;
    }
}
