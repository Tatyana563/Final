package com.home.sphibcity.dto.response;

import com.home.sphibcity.model.CityEntity;

import java.util.Objects;

public class CityResponse {
    String name;
    String type;
    String description;
    Boolean capital;
    Integer countryId;// shall it be there???
    Integer population;
    Double square;

    public CityResponse(CityEntity cityEntity){
        if(Objects.isNull(cityEntity)) return;
       this.name=cityEntity.getName();
       this.type=cityEntity.getType().name();
       this.description=cityEntity.getDescription();
       this.capital=cityEntity.getCapital();
     this.countryId=cityEntity.getCountryId();// shall it be there???
      this.population=cityEntity.getPopulation();
       this.square=cityEntity.getSquare();
    }
}
