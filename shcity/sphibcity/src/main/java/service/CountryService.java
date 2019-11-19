package service;

import model.CountryEntity;
import org.hibernate.cache.spi.FilterKey;

import java.util.Collection;
import java.util.Map;

public interface CountryService extends GenericService<CountryEntity, Integer> {
    Collection<CountryEntity> findAll(int position, int limit);


}
