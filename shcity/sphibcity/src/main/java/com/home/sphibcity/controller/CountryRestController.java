package com.home.sphibcity.controller;

import com.home.sphibcity.dto.request.CountryRequest;
import com.home.sphibcity.dto.response.CountryResponse;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryRestController {
    @Autowired
    private CountryService countryService;
    @GetMapping("/add")
    public CountryResponse createOrUpdate(
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("currency") String currency,
            @RequestParam("language") String language,
            @RequestParam("description") String description,
            @RequestParam("population") Integer population,
            @RequestParam("square") Double square){
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(id);
        countryEntity.setPopulation(population);
        countryEntity.setName(name);
        countryEntity.setSquare(square);
        countryEntity.setDescription(description);
        countryEntity.setLanguage(Language.valueOf(language));
        countryEntity.setCurrency(Currency.valueOf(currency));
        return new CountryResponse(countryEntity);// where would be the list of cities?
    }
    //http://localhost:8080/city/10
    @GetMapping("{deleteId}")
    public void deleteById(@PathVariable("deleteId") int id){
        countryService.remove(id);
    }
//difference???
    @DeleteMapping("{countryId}")
    public CountryRequest deleteCountryById(@PathVariable("countryId") int countryId) {
        return countryService.deleteByIdRequest(countryId);
    }
//http://localhost:8080/city?getId=1 ?
    @GetMapping("/{getId}")
    public void getCountry(@PathVariable("getId") int id){
        countryService.findById(id);
    }
    @PostMapping
    public CountryResponse createFromPost(@RequestBody CountryRequest countryRequest) {
        CountryEntity countryEntity = countryRequest.convert();
        countryService.createOrUpdate(countryEntity);
        final CountryEntity country = countryService.findById(countryEntity.getId()).get();
        return new CountryResponse(country);
    }


}
