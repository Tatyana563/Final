package dao;

import model.CityEntity;
import model.CountryEntity;

import java.util.Collection;

public interface CountryDao extends GenericDao<CountryEntity, Integer> {
    Collection<CityEntity> getAllCitiesByCountry(int countryId);
}
