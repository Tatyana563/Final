package com.home.sphibcity.service;

import com.home.sphibcity.model.CountryEntity;

import java.util.Collection;

public interface CountryService extends GenericService<CountryEntity, Integer> {
    Collection<CountryEntity> findAll(int position, int limit);


}
