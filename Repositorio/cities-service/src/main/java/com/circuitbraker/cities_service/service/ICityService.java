package com.circuitbraker.cities_service.service;

import com.circuitbraker.cities_service.dto.CityDTO;

public interface ICityService {

    public CityDTO getCitiesHotels(String name, String country);
}
