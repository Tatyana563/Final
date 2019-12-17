package com.home.sphibcity.controller;

import com.home.sphibcity.dto.request.CountryRequest;
import com.home.sphibcity.dto.response.CountryResponse;
import com.home.sphibcity.model.CountryEntity;
import com.home.sphibcity.model.enumeration.Currency;
import com.home.sphibcity.model.enumeration.Language;
import com.home.sphibcity.repository.CountryRepository;
import com.home.sphibcity.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryRestController {
    @Autowired
    private CountryService countryService;
   // http://localhost:8080/country/add?name=newCountryName22&currency=EUR&language=English&description=descr%20test&population=1000&square=10.0

    @GetMapping("/add")
    public CountryResponse createOrUpdate(
            @RequestParam(value="id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam(value = "currency", required = false) String currency,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "population",required = false) Integer population,
            @RequestParam(value = "square", required = false) Double square){
        final CountryEntity countryEntity = new CountryEntity();
        countryEntity.setId(id);
        countryEntity.setPopulation(population);
        countryEntity.setName(name);
        countryEntity.setSquare(square);
        countryEntity.setDescription(description);
        countryEntity.setLanguage(Language.valueOf(language));
        countryEntity.setCurrency(Currency.valueOf(currency));
        countryService.createOrUpdate(countryEntity);
        return new CountryResponse(countryEntity);
    }
    //
    @GetMapping("/delete/{deleteId}")
    public ResponseEntity deleteById(@PathVariable("deleteId") int id){
        countryService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<CountryResponse>> findAll(){
        Collection<CountryEntity> countryEntities = countryService.findAll(0, 100);
        if (countryEntities.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Collection<CountryResponse> responses = countryEntities.stream().map(item->new CountryResponse(item)).collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        }
    }
//difference???
    @DeleteMapping("{countryId}")
    public ResponseEntity deleteCountryById(@PathVariable("countryId") int countryId) {
        countryService.remove(countryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{getId}")
    public void getCountry(@PathVariable("getId") int id){
        countryService.findById(id);
    }

    @PostMapping("/update")
    public ResponseEntity<CountryResponse> createFromPost(@RequestBody CountryRequest countryRequest) {
        CountryEntity countryEntity = countryRequest.convert();
        countryService.createOrUpdate(countryEntity);
        final CountryEntity country = countryService.findById(countryEntity.getId()).get();
        return ResponseEntity.ok(new CountryResponse(country));
    }
//in case I need one extra @PostMapping
 /*   @PostMapping("/update2")
    public CountryResponse createFromPost2(@RequestBody CountryRequest countryRequest) {
        CountryEntity countryEntity = countryRequest.convert();
        countryService.createOrUpdate(countryEntity);
        final CountryEntity country = countryService.findById(countryEntity.getId()).get();
        return new CountryResponse(country);*/
    }




