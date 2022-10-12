package com.ivpo.beerchallenge.dto;

import java.util.List;
import java.util.Objects;

public class BeerDto {

    private Long id;

    private String name;

    private String description;

    private Double meanTemp;

    private List<TemperatureDto> temperatures;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMeanTemp() {
        return meanTemp;
    }

    public void setMeanTemp(Double meanTemp) {

        this.meanTemp = meanTemp;
    }

    public List<TemperatureDto> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<TemperatureDto> temperatures) {
        this.temperatures = temperatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BeerDto that = (BeerDto) o;
        return id.equals(that.id) &&
                Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(meanTemp, that.meanTemp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, meanTemp);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("BeerDto(")
                .append(" id= ").append(id).append(",")
                .append(" name= ").append(name).append(",")
                .append(" description= ").append(description).append(",")
                .append(" temperature= ").append(meanTemp)
                .append(" )");
        return stringBuilder.toString();
    }
}
