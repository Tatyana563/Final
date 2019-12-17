package com.home.sphibcity.dto.response;

import com.home.sphibcity.model.CountryEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

//@Setter
@Getter
@ToString
public class CountryResponse {

    private Integer id;
    private String name;
    private String currency;
    private String language;
    private String description;
    private Integer population;
    private Double square;
    private Collection<CityResponse> cities = new ArrayList<>();

    public CountryResponse() {
    }

    public CountryResponse(CountryEntity countryEntity) {
        if (Objects.isNull(countryEntity)) return;
        this.id = countryEntity.getId();
        this.name = countryEntity.getName();
        this.currency = countryEntity.getCurrency().name();
        this.language = countryEntity.getLanguage().name();
        this.population = countryEntity.getPopulation();
        this.description = countryEntity.getDescription();
        this.square = countryEntity.getSquare();
        if (countryEntity.getCityList() != null && !countryEntity.getCityList().isEmpty()) {
            countryEntity.getCityList().forEach(item -> this.cities.add(new CityResponse(item)));
        }


    }
}
