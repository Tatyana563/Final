package com.home.sphibcity.controller;

import com.home.sphibcity.model.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.home.sphibcity.service.CityService;

import java.util.Collection;
// controller listens to requests from the Internet and directs to proper
// methods.
//in pom.xml add <finalName>city</finalName> it would be the name -> city.war
//Maven->clean+package run->see .war in target.In tomcat folder -> webapp-> copy .war here
//in tomcat folder startup.bat.

@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/all-cities")
    public Collection<CityEntity> getAll() {
        return cityService.findAll();

    }
}
