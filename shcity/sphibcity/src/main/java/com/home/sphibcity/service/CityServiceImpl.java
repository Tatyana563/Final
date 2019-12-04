package com.home.sphibcity.service;


import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.repository.CityRepository;
import com.home.sphibcity.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service("cityService")
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;



    @Override
    @Transactional
    public void createOrUpdate(CityEntity entity)
    {
cityRepository.save(entity);
    }

    @Override
    @Transactional
    public Collection<CityEntity> findAll()
    {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<CityEntity> findById(int id) {
        Optional<CityEntity> city=cityRepository.findById(id);
        return Optional.ofNullable(city.orElse(new CityEntity()));
    }

    @Override
    @Transactional
    public void remove(int id) {
CityEntity cityEntity = findById(id).get();
cityRepository.delete(cityEntity);
    }

    @Override
    @Transactional
    public void updateCityPopulation(int cityId, int cityPopulation) {
        /*Optional<CityEntity> cityEntity = cityRepository.findById(cityId);
        if (cityEntity.isPresent()) {
            CityEntity city = cityEntity.get();
            city.setPopulation(cityPopulation);
            cityRepository.save(city);
        }*/
        cityRepository.updateCityPopulation(cityId, cityPopulation);
    }
    @Override
    @Transactional
    public CityRequest deleteByIdRequest(int id) {
        CityEntity cityEntity =findById(id).get();
       cityRepository.delete(cityEntity);
        return null;
    }
}
