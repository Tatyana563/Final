package com.home.sphibcity.dto.response;

import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.model.CityEntity;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CityResponse extends CityRequest {


    public CityResponse(CityEntity cityEntity) {
        if (Objects.isNull(cityEntity)) return;
        name = cityEntity.getName();
        setType(cityEntity.getType().name());
        description = cityEntity.getDescription();
        setCapital(cityEntity.getCapital());
        setCountryId(cityEntity.getCountryId());
        population = cityEntity.getPopulation();
        square = cityEntity.getSquare();
    }
}
