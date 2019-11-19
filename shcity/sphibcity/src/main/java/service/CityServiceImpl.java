package service;

import dao.CityDao;
import dao.CountryDao;
import model.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private CountryDao countryDao;



    @Override
    public void createOrUpdate(CityEntity entity) {
cityDao.save(entity);
    }

    @Override
    public Collection<CityEntity> findAll() {
        return cityDao.findAll();
    }

    @Override
    public Optional<CityEntity> findById(int id) {
        Optional<CityEntity> city=cityDao.findById(id);
        return Optional.ofNullable(city.orElse(new CityEntity()));
    }

    @Override
    @Transactional
    public void remove(int id) {
CityEntity cityEntity = findById(id).get();
cityDao.delete(cityEntity);
    }

    @Override
    @Transactional
    public void updateCityPopulation(int cityId, int cityPopulation) {
        cityDao.updateCityPopulation(cityId,cityPopulation);
    }
}
