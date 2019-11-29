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
    @Override
    @Transactional
    public Collection<CountryEntity> findAll(int position, int limit) {
       countryRepository.findAll();
        return null;
    }

    @Override
    public void createOrUpdate(CountryEntity entity) {
countryRepository.save(entity);
    }

    @Override
    public Collection<CountryEntity> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<CountryEntity> findById(int id) {
        Optional<CountryEntity> city=countryRepository.findById(id);
        return Optional.ofNullable(city.orElse(new CountryEntity()));
    }

    @Override
    public void remove(int id) {
Optional<CountryEntity> countryEntity=findById(id);
if(countryEntity.isPresent()){
   countryRepository.delete(countryEntity.get());
}
    }

}
