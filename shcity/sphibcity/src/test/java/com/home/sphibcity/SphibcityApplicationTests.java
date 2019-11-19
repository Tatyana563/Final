package com.home.sphibcity;

import model.CityEntity;
import model.CountryEntity;
import model.enumeration.Currency;
import model.enumeration.Language;
import model.enumeration.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.test.context.junit4.SpringRunner;
import service.CityServiceImpl;
import service.CountryServiceImpl;

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

	}
	@Test
	void contextLoads2(){
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
