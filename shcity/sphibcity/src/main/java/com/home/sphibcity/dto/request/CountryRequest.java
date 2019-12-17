package com.home.sphibcity.dto.request;

import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryRequest extends GeneralRequest {

    private String currency;
    private String language;

    public CountryEntity convert() {
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCurrency(Currency.valueOf(this.getCurrency()));
        countryEntity.setLanguage(Language.valueOf(this.getLanguage()));
        countryEntity.setDescription(super.getDescription());
        countryEntity.setPopulation(super.getPopulation());
        countryEntity.setSquare(super.getSquare());
        countryEntity.setId(super.getId());
        countryEntity.setName(name);
        return countryEntity;
    }

}


