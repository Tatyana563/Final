package com.home.sphibcity.service;


import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service

public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private TaskServiceImpl taskService;

    @Override
    @Transactional(readOnly = true)
    public Collection<CountryEntity> findAll(int position, int limit) {
        return countryRepository.findAll();
    }

    @Override
    public void createOrUpdate(CountryEntity entity) {
        final boolean isNew = entity.getId() == null;
        countryRepository.save(entity);
        taskService.createTask(entity.getId(), isNew);
    }

    @Override
    public Collection<CountryEntity> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<CountryEntity> findById(int id) {
        Optional<CountryEntity> city = countryRepository.findById(id);
       return Optional.ofNullable(city.orElse(new CountryEntity()));
       // return Optional.ofNullable(city.orElse("empty"));
    }

    @Override
    public void remove(int id) {
        Optional<CountryEntity> countryEntity = findById(id);
        if (countryEntity.isPresent()) {
            countryRepository.delete(countryEntity.get());
        }
    }

}
