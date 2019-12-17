package com.home.sphibcity.controller;

import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.dto.request.Pair;
import com.home.sphibcity.dto.response.CityResponse;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.enumeration.Type;
import com.home.sphibcity.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

// controller listens to requests from the Internet and directs to proper
// methods.
//in pom.xml add <finalName>city</finalName> it would be the name -> city.war
//Maven->clean+package run->see .war in target.In tomcat folder -> webapp-> copy .war here
//in tomcat folder startup.bat.
//http://localhost:8080/all-cities
//http://localhost:8080//create-city?name=name1&type=GLOBAL_GIANT
@RestController
@RequestMapping("/city")
public class CityRestController {

    @Autowired
    private CityService cityService;

    //    @Qualifier("cityService")
//    private CityService cityService;
    @GetMapping("/add")
//http://localhost:8080/city/add?name=newCity&countryId=1&population=123&description=nice&type=GLOBAL_GIANT&capital=false&square=25.36
    public CityResponse createOrUpdate(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("countryId") Integer countryId,
            @RequestParam(value = "population", required = false) Integer population,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "capital", required = false) Boolean capital,
            @RequestParam(value = "square", required = false) Double square) {
        final CityEntity cityEntity = new CityEntity();
        cityEntity.setName(name);
        cityEntity.setId(id);
        cityEntity.setCountryId(id);
        cityEntity.setSquare(square);
        cityEntity.setCapital(capital);
        cityEntity.setDescription(description);
        cityEntity.setPopulation(population);
        cityEntity.setType(Type.valueOf(type));
        return new CityResponse(cityEntity);
    }

    //http://localhost:8080/city/delete/16
    @GetMapping("/delete/{deleteId}")
    public ResponseEntity deleteById(@PathVariable("deleteId") int id) {
        cityService.remove(id);
        return ResponseEntity.ok().build();
    }

    //http://localhost:8080/city/all
    @GetMapping("/all")
    public ResponseEntity<Collection<CityResponse>> findAll() {
        Collection<CityEntity> cityEntity = cityService.findAll();
        if (cityEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Collection<CityResponse> responses = cityEntity.stream().map(item -> new CityResponse(item)).collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        }
    }

    //http://localhost:8080/city/getById/15
    @GetMapping("/getById/{getId}")
    public ResponseEntity<CityResponse> getCity(@PathVariable("getId") int id) {
        Optional<CityEntity> cityEntityOptional = cityService.findById(id);
        if (cityEntityOptional.isPresent()) {
            return ResponseEntity.ok(new CityResponse(cityEntityOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/updateCityPopulation")
    public CityResponse createFromPost(@RequestBody CityRequest cityRequest) {
        CityEntity cityEntity = cityRequest.convert();
        cityService.createOrUpdate(cityEntity);
        final CityEntity city = cityService.findById(cityEntity.getId()).get();
        return new CityResponse(city);

    }

    @PutMapping
    public void updatePopulation(@RequestBody Pair<Integer, Integer> data) {
        cityService.updateCityPopulation(data.getId(), data.getValue());
    }

}










