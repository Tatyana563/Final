package com.home.sphibcity.controller;

import com.home.sphibcity.dto.request.CityRequest;
import com.home.sphibcity.dto.response.CityResponse;
import com.home.sphibcity.model.CityEntity;
import com.home.sphibcity.model.enumeration.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.home.sphibcity.service.CityService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.validation.Valid;
import java.util.Collection;
// controller listens to requests from the Internet and directs to proper
// methods.
//in pom.xml add <finalName>city</finalName> it would be the name -> city.war
//Maven->clean+package run->see .war in target.In tomcat folder -> webapp-> copy .war here
//in tomcat folder startup.bat.
//http://localhost:8080/all-cities
//http://localhost:8080//create-city?name=name1&type=GLOBAL_GIANT
@RestController
public class CityRestController {

    @Autowired
    @Qualifier("cityService")
    private CityService cityService;

    @GetMapping("/all-cities")
    public Collection<CityEntity> getAll(HttpServletRequest req, HttpServletResponse resp) {
        return cityService.findAll();

    }
    @GetMapping("/create-city")
    public void create (HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("name") String name,
                        @RequestParam("type") String type
                        ) {

     CityEntity city = new CityEntity();
     city.setName(name);
     city.setType(Type.valueOf(type));
     //city.setType(Enum.valueOf(Type.class, type));

      cityService.createOrUpdate(city);


        }
        //@RequestBody annotation maps the HttpRequest body to a transfer or domain object,
        // enabling automatic deserialization of the inbound HttpRequest body onto a
        // Java object.
@PostMapping
    public CityResponse createFromPost(@RequestBody CityRequest cityRequest){
        CityEntity cityEntity = cityRequest.convert();
        cityService.createOrUpdate(cityEntity);
        final CityEntity city = cityService.findById(cityEntity.getId()).get();
return new CityResponse(city);
    }
    @DeleteMapping("{cityId}")
    public CityRequest deleteCityById(@PathVariable("cityId") int cityId){
        return cityService.deleteByIdRequest(cityId);
    }
    }

