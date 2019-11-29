package com.home.sphibcity.repository;

import com.home.sphibcity.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity,Integer>, JpaSpecificationExecutor<CityEntity> {

    @Modifying
    @Query("update CityEntity c set c.population = :cityPopulation where c.id = :id")
    void updateCityPopulation(@Param("id") int cityId, @Param("cityPopulation") int cityPopulation);
}

