package service;

import dao.CountryDao;
import model.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
@Service

public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Override
    @Transactional
    public Collection<CountryEntity> findAll(int position, int limit) {
        countryDao.findAll();
        return null;
    }

    @Override
    public void createOrUpdate(CountryEntity entity) {
countryDao.save(entity);
    }

    @Override
    public Collection<CountryEntity> findAll() {
        return countryDao.findAll();
    }

    @Override
    public Optional<CountryEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void remove(int id) {
Optional<CountryEntity> countryEntity=findById(id);
if(countryEntity.isPresent()){
    countryDao.delete(countryEntity.get());
}
    }
}
