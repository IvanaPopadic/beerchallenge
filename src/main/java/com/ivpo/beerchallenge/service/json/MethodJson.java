package com.ivpo.beerchallenge.service.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MethodJson {
    @JsonProperty("mash_temp")
    List<MashTempJson> mashTempsJson;

    public List<MashTempJson> getMashTempsJson() {
        return mashTempsJson;
    }

    public void setMashTempsJson(List<MashTempJson> mashTempsJson) {
        this.mashTempsJson = mashTempsJson;
    }
}
