package com.home.sphibcity;

import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.service.CountryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SphibcountryApplicationTest {
    @Autowired
    CountryServiceImpl countryService;
    private CountryEntity countryEntity = new CountryEntity();

    public void init() {
        countryEntity.setName("Italy");
        countryEntity.setPopulation(200);
        countryEntity.setDescription("a tourist destination");
        countryEntity.setLanguage(Language.Italian);
        countryEntity.setSquare(236.5);
        countryService.createOrUpdate(countryEntity);

    }
//example for a thread pool
    @Test
    public void taskTEst() throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleWithFixedDelay(()->System.out.println("run task " + Instant.now()), 1, 2, TimeUnit.SECONDS);
        Thread.sleep(10000);
    }

    private CountryEntity buildCountry(String name, int population, double square, String description, Currency currency, Language laguage) {
        CountryEntity country = new CountryEntity();
        country.setName(name);
        country.setCurrency(currency);
        country.setLanguage(laguage);
        country.setDescription(description);
        country.setPopulation(population);
        return country;
    }

    private CountryEntity createNewCountry(CountryEntity country) {
        countryService.createOrUpdate(country);
        return countryService.findById(country.getId()).get();
    }

    @Test
    public void shouldCreateCountryTest() {
        init();
        Assert.assertNotNull(countryEntity.getId());
    }

    @Test
    public void shouldUpdateCountryTest() {
        init();
        CountryEntity beforeCountryEntity = countryService.findById(countryEntity.getId()).get();
        Assert.assertTrue(countryEntity.equals(beforeCountryEntity));
        beforeCountryEntity.setName("Italy");
        beforeCountryEntity.setPopulation(888);
        beforeCountryEntity.setLanguage(Language.German);
        countryService.createOrUpdate(beforeCountryEntity);
        Assert.assertFalse(countryEntity.equals(beforeCountryEntity));
    }

    @Test
    public void shouldDeleteCountryTest() {
        init();
        countryService.remove(countryEntity.getId());
        Assert.assertEquals(countryService.findById(countryEntity.getId()).get().getId(), null);
    }

    @Test
    public void shouldGetAllCountries() {
        CountryEntity country = buildCountry("test", 1111, 22.2, "description", Currency.FRF, Language.Spanish);
        List<CountryEntity> created = new ArrayList<>();
        created.add(createNewCountry(country));

        country = buildCountry("test2", 1111, 22.2, "description", Currency.FRF, Language.Spanish);
        created.add(createNewCountry(country));

        country = buildCountry("test3", 1111, 22.2, "description", Currency.FRF, Language.Spanish);
        created.add(createNewCountry(country));

        Collection<CountryEntity> countries = countryService.findAll();
        Assert.assertFalse(countries.isEmpty());
        System.out.println(created.size());
        Assert.assertThat(countries.stream().filter(item -> created.contains(item)).count(), is(3L));
    }

}
