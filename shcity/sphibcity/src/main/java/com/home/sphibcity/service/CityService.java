package com.home.sphibcity.service;

import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.model.CityEntity;

public interface CityService extends GenericService<CityEntity, Integer> {
    void updateCityPopulation(int cityId, int cityPopulation);

}
