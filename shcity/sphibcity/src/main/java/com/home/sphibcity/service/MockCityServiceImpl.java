package com.home.sphibcity.service;


import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.repository.CityRepository;
import com.home.sphibcity.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Service("mockCityService")
public class MockCityServiceImpl implements CityService {

    @Value("${app.city.name.default:test}")
    private String defaultCityName;

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public CityEntity getEmptyCity() {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(new Random().nextInt());
        cityEntity.setName(defaultCityName);
        return cityEntity;
    }

    @Autowired
    private ApplicationContext context;


    @Override
    public void updateCityPopulation(int cityId, int cityPopulation) {

    }



    @Override
    public void createOrUpdate(CityEntity entity) {

    }

    @Override
    public Collection<CityEntity> findAll() {
        CityEntity city = context.getBean("getEmptyCity", CityEntity.class);
        return Collections.singleton(city);
    }

    @Override
    public Optional<CityEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void remove(int id) {

    }
}
