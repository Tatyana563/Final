package com.home.sphibcity.controller;

import com.home.sphibcity.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
//@RequestMapping("/api/city") - required?
public class CityController {

    @Autowired
    private CityService cityService;

  /*  @GetMapping("/main")
    public ModelAndView main(Map<String, Object> model) {
        model.put("cities", cityService.findAll());
        return new ModelAndView("index", model);
    }*/


}
