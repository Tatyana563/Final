package com.home.sphibcity;

import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Type;
import com.home.sphibcity.service.CityServiceImpl;
import com.home.sphibcity.service.CountryServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
class SphibcityApplicationTests {
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    CountryServiceImpl countryService;

    private CityEntity cityEntity = new CityEntity();

   @Before
   public void init() {
       final CountryEntity countryEntity = new CountryEntity();
       countryEntity.setId(1);
       cityEntity.setCapital(true);
       cityEntity.setName("Paris");
       cityEntity.setDescription("Romantic and green2");
       cityEntity.setType(Type.KNOWLEDGE_CAPITAL);
       cityEntity.setPopulation(800);
       cityEntity.setSquare(550.00);
       cityEntity.setCountryEntity(countryEntity);
       cityService.createOrUpdate(cityEntity);
    }

    private CityEntity buildCity(final String name, final String descr, final Type type, final CountryEntity country) {
       CityEntity city = new CityEntity();
       city.setName(name);
       city.setDescription(descr);
       city.setType(type);
       city.setCountryEntity(country);
       return city;
    }
    @Test
    private CityEntity createNewCity(CityEntity city) {
       cityService.createOrUpdate(city);
       return cityService.findById(city.getId()).get();
    }

    @Test
    public void shouldGetAllCities() {
       CityEntity city = buildCity("test1", "test1", Type.GLOBAL_GIANT, new CountryEntity(1));
        List<CityEntity> created = new ArrayList<>(3);
       created.add(createNewCity(city));
        city = buildCity("test2", "test2", Type.GLOBAL_GIANT, new CountryEntity(1));
        created.add(createNewCity(city));
        city = buildCity("test3", "test3", Type.GLOBAL_GIANT, new CountryEntity(1));
        created.add(createNewCity(city));

        Collection<CityEntity> cities = cityService.findAll();
        Assert.assertFalse(cities.isEmpty());
        Assert.assertThat(cities.stream().filter(item->created.contains(item)).count(), is(3L));
    }


    @Test
    public void shouldCreateCityTest() {
        init();
        Assert.assertNotNull(cityEntity.getId());
    }

    @Test
    public void shouldUpdateCityTest() {
        init();
        CityEntity beforeCityEntity = cityService.findById(cityEntity.getId()).get();
        Assert.assertTrue(cityEntity.equals(beforeCityEntity));
        beforeCityEntity.setName("new test name");
        beforeCityEntity.setType(Type.GLOBAL_GIANT);
        beforeCityEntity.setCountryEntity(null);
        beforeCityEntity.setCapital(false);
        cityService.createOrUpdate(beforeCityEntity);
        Assert.assertFalse(cityEntity.equals(beforeCityEntity));
   }

    @Test
    void shouldUpdateCityPopulationTest() {
        init();
        int oldPopulation = cityEntity.getPopulation();
        cityService.updateCityPopulation(cityEntity.getId(), 12345676);
        cityEntity = cityService.findById(cityEntity.getId()).get();
        Assert.assertThat(cityEntity.getPopulation(), Matchers.not(oldPopulation));
    }

    @Test
    public void shouldDeleteCityTest() {
        init();
        cityService.remove(cityEntity.getId());
        System.out.println(cityService.findById(cityEntity.getId()));//Optional.empty
Assert.assertEquals(cityService.findById(cityEntity.getId()), Optional.empty());

   }

}
