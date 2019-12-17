package com.home.sphibcity;

import com.home.sphibcity.dto.request.CountryRequest;
import com.home.sphibcity.dto.response.CountryResponse;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

//@ActiveProfiles("postgres")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SphibcityApplication.class
)
public class TestCountryController {
    private RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    @Test
    public void testDeleteCountryRestRequest() {
      restTemplate.delete(URI.create("http://localhost:" + port +"/country/4"));

    }

    @Test
    public void testPostCountryRestRequest() {
        CountryRequest request = new CountryRequest();
        request.setPopulation(1000);
        request.setCurrency(Currency.EUR.name());
        request.setDescription("a friendly country");
        request.setLanguage(Language.Italian.name());
        request.setSquare(269.3);
        request.setName("Italy"+System.currentTimeMillis());
        ResponseEntity<CountryResponse> response =
                restTemplate.postForEntity(URI.create("http://localhost:" + port + "/country/update"), request, CountryResponse.class);

        System.out.println(response.getBody());
    }
//What request?
    @Test
        public void testGetCountryRestController() {
        CountryRequest request = new CountryRequest();
        request.setPopulation(1000);
        request.setCurrency(Currency.EUR.name());
        request.setDescription("a friendly country");
        request.setLanguage(Language.Italian.name());
        request.setSquare(269.3);
        request.setName("Italy"+System.currentTimeMillis());

        ResponseEntity<CountryResponse> responseEntity =
                restTemplate.getForEntity(URI.create("http://localhost:" + port + "/country/" + request.getId()), CountryResponse.class);
    }
}
