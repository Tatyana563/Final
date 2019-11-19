package dao;

import model.CityEntity;

public interface CityDao extends GenericDao<CityEntity,Integer> {
    void updateCityPopulation(int cityId, int cityPopulation);
}
