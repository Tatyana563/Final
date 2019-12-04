package com.home.sphibcity;

import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.model.enumeration.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.home.sphibcity.service.CityServiceImpl;
import com.home.sphibcity.service.CountryServiceImpl;
import org.springframework.util.Assert;

//@RunWith(SpringRunner.class)
@SpringBootTest
class SphibcityApplicationTests {
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    CountryServiceImpl countryService;

    @Test
    void contextLoads() {
        final CityEntity cityEntity = new CityEntity();
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        cityEntity.setCapital(false);
        cityEntity.setName("Drezden");
        //cityEntity.setDescription("Marvelous architecture");
        cityEntity.setType(Type.KNOWLEDGE_CAPITAL);
        //cityEntity.setPopulation(1000);
        //cityEntity.setSquare(500.00);
        cityService.createOrUpdate(cityEntity);

        cityService.updateCityPopulation(cityEntity.getId(), 12345);
        Assertions.assertTrue(cityService.findById(cityEntity.getId()).get().getPopulation() == 12345);

    }

    @Test
    void contextLoads2() {
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(1);
        countryEntity.setCurrency(Currency.EUR);
        countryEntity.setLanguage(Language.German);
        countryEntity.setCurrency(Currency.DM);
        //countryEntity.setDescription("A nice country");
        //countryEntity.setPopulation(2000);
        //countryEntity.setSquare(5000.00);
        countryService.createOrUpdate(countryEntity);
    }

}
