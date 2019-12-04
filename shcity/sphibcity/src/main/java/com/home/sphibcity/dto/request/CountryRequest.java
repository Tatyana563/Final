package com.home.sphibcity.dto.request;

import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import lombok.Getter;
import lombok.Setter;


    @Setter
    @Getter
    public class CountryRequest {
        private Integer id;
        private String name;
        private String currency;
        private String language;
        private String description;
        private Integer population;
        private Double square;

        public CountryEntity convert(){
            final CountryEntity countryEntity = new CountryEntity();
            countryEntity.setCurrency(Currency.valueOf(this.getCurrency()));
            countryEntity.setLanguage(Language.valueOf(this.getLanguage()));
            countryEntity.setDescription(this.description);
            countryEntity.setPopulation(this.population);
            countryEntity.setSquare(this.square);
            countryEntity.setId(this.id);
            return countryEntity;
        }
    }


