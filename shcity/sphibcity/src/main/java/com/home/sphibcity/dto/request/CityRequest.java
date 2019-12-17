package com.home.sphibcity.dto.request;

import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Type;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityRequest extends GeneralRequest {

    private String type;
    private Boolean capital;
    private Integer countryId;

    public CityEntity convert() {
        final CityEntity cityEntity = new CityEntity();
        cityEntity.setType(Type.valueOf(type));
        cityEntity.setName(name);
        cityEntity.setPopulation(population);
        cityEntity.setDescription(description);
        cityEntity.setCapital(capital);
        cityEntity.setId(id);
        cityEntity.setCountryEntity(new CountryEntity(countryId));
        cityEntity.setSquare(square);
        return cityEntity;
    }
}
