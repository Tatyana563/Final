package com.home.sphibcity;

import com.home.sphibcity.dto.request.CountryRequest;
import com.home.sphibcity.dto.request.Pair;
import com.home.sphibcity.dto.response.CountryResponse;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SphibcityApplication.class
)
public class TestCityController {

    private RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    @Test
    public void testPutCityRestRequest() {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("id", 15);
        parameters.put("value", 1500);
        Pair<Integer, Integer> data = new Pair<>(15, 1500);
        //restTemplate.put(URI.create("http://localhost:" + port + "/city"), parameters);
        restTemplate.put(URI.create("http://localhost:" + port + "/city"), data);

    }

    @Test
    public void testDeleteCountryRestRequest() {
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setName("test simple name2");
        countryRequest.setSquare(12.d);
        countryRequest.setLanguage(Language.Italian.name());
        countryRequest.setCurrency(Currency.EUR.name());
        CountryResponse countryResponse = restTemplate.postForEntity(URI.create("http://localhost:" + port + "/country/update"), countryRequest, CountryResponse.class).getBody();
      // restTemplate.delete(URI.create("http://localhost:" + port + "/country/" + countryResponse.getId()));
        countryResponse = restTemplate.getForObject(URI.create("http://localhost:" + port + "/country/" + countryResponse.getId()), CountryResponse.class);
       // Assert.isNull(countryResponse);*/

    }
}
