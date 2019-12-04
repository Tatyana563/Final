package com.home.sphibcity.dto.request;

import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.enumeration.Type;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class CityRequest {
    Integer id;
    String name;
    String type;
    String description;
    Boolean capital;
    Integer countryId;
    Integer population;
    Double square;

    public CityEntity convert(){
  final CityEntity cityEntity = new CityEntity();
  cityEntity.setType(Type.valueOf(this.getType()));
  cityEntity.setName(this.getName());
  cityEntity.setPopulation(this.getPopulation());
  cityEntity.setDescription(this.description);
  cityEntity.setCapital(this.getCapital());
  cityEntity.setId(this.getId());
  cityEntity.setCountryId(this.getCountryId());
  cityEntity.setSquare(this.getSquare());
 return cityEntity;
    }
}
