package com.ivpo.beerchallenge.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "beer_temperature")
public class BeerTemperatureEntity {
    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temp_value")
    private Double tempValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "temp_unit")
    private TemperatureUnit tempUnit;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "beer_id", nullable = false)
    private BeerEntity beer;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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
       this.tempUnit=tempUnit;
    }

    @ManyToOne
    public BeerEntity getBeer() {
        return beer;
    }

    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerTemperatureEntity that = (BeerTemperatureEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(beer.getId(), that.beer.getId())
                && Objects.equals(tempValue, that.tempValue)
                && Objects.equals(tempUnit, that.tempUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beer.getId(), tempValue, tempUnit);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder()
                .append("BeerTemperatureEntity(")
                .append(" id= ").append(id).append(",")
                .append(" beerId= ").append(beer.getId()).append(",")
                .append(" tempValue= ").append(tempValue).append(",")
                .append(" tempUnit= ").append(tempUnit)
                .append(" )");
        return stringBuilder.toString();
    }
}
