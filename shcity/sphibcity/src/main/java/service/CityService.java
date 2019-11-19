package service;

import model.CityEntity;

public interface CityService extends GenericService<CityEntity, Integer> {
    void updateCityPopulation(int cityId, int cityPopulation);
}
