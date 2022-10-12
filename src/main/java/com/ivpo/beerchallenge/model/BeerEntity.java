package com.ivpo.beerchallenge.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "beer")
public class BeerEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "temperature")
    private Double temperature;

    @OneToMany(mappedBy = "beer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BeerTemperatureEntity> beerTemperatureEntities;

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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {

        this.temperature = temperature;
    }

    @OneToMany
    public List<BeerTemperatureEntity> getBeerTemperatureEntities() {
        return beerTemperatureEntities;
    }

    public void setBeerTemperatureEntities(List<BeerTemperatureEntity> beerTemperatureEntities) {
        this.beerTemperatureEntities = beerTemperatureEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final BeerEntity that = (BeerEntity) o;
        return id.equals(that.id) &&
                Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, temperature);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("BeerEntity(")
                .append(" id= ").append(id).append(",")
                .append(" name= ").append(name).append(",")
                .append(" description= ").append(description).append(",")
                .append(" temperature= ").append(temperature)
                .append(" )");
        return stringBuilder.toString();
    }
}
