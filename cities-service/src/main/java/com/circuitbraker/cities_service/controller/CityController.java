package com.circuitbraker.cities_service.controller;

import com.circuitbraker.cities_service.dto.CityDTO;
import com.circuitbraker.cities_service.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityServ;

    @GetMapping("/hotels")
    public CityDTO getCityAndHotels(@RequestParam String name, @RequestParam String country){
        return cityServ.getCitiesHotels(name,country);
    }


}
