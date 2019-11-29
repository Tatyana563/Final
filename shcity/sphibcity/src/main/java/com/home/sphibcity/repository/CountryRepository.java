package com.home.sphibcity.repository;

import com.home.sphibcity.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer>, JpaSpecificationExecutor<CountryEntity> {
}
